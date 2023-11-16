package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.dto.CallbackQueryData;
import com.tenagrim.telegram.dto.integration.amocrm.*;
import com.tenagrim.telegram.dto.integration.amocrm.auth.AccessTokenResponse;
import com.tenagrim.telegram.dto.integration.amocrm.auth.RefreshAccessTokenRequest;
import com.tenagrim.telegram.enums.AmoCrmGrantType;
import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.TGUser;
import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.model.chapter.Paragraph;
import com.tenagrim.telegram.model.config.BotConfig;
import com.tenagrim.telegram.model.integration.Integration;
import com.tenagrim.telegram.model.integration.IntegrationQueueRecord;
import com.tenagrim.telegram.model.integration.IntegrationTrigger;
import com.tenagrim.telegram.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static com.tenagrim.telegram.enums.IntegrationCredentialType.*;
import static com.tenagrim.telegram.enums.IntegrationEndpoints.ACCESS_TOKEN;
import static com.tenagrim.telegram.enums.IntegrationEndpoints.AMO_ADD_COMPLEX_LEADS;
import static com.tenagrim.telegram.enums.IntegrationType.AMO_CRM;

@Service
@RequiredArgsConstructor
public class IntegrationService {

    private final IntegrationQueueRepository queueRepository;
    private final ParagraphRepository paragraphRepository;
    private final BotConfigRepository botConfigRepository;
    private final TGUserRepository tgUserRepository;

    public void pushRecords(BotConfig botConfig, Chapter chapter, Long userId, CallbackQueryData callbackQueryData) {
        for (IntegrationTrigger t : chapter.getIntegrationTriggers()) {
            if (botConfig.getIntegrations().stream().allMatch(i -> Objects.equals(i.getTypeId(), t.getIntegrationTypeId()))) {
                queueRepository.save(IntegrationQueueRecord.builder()
                        .userId(userId)
                        .chapterId(callbackQueryData.getFrom())
                        .integrationTypeId(t.getIntegrationTypeId())
                        .actual(true)
                        .build());
            }
        }
    }

    @Transactional
    public void sendContact(BotConfig botConfig, User user, Contact contact) {
        List<IntegrationQueueRecord> integrationQueueRecords = queueRepository.findDistinctActualByUserId(user.getId());
        for (IntegrationQueueRecord r : integrationQueueRecords) {
            if (Objects.equals(r.getIntegrationTypeId(), AMO_CRM.getId())) { // TODO: enum
                Integration amoIntegration = botConfig.getIntegrations().stream()
                        .filter(i -> AMO_CRM.getId().equals(i.getTypeId()))
                        .findFirst().orElseThrow();
                sendToAmo(botConfig, amoIntegration, r, user, contact);
            }
        }
        queueRepository.setInactiveByUserId(user.getId());
    }

    private void sendToAmo(BotConfig botConfig, Integration amoIntegration, IntegrationQueueRecord record, User user, Contact contact) {
        // TODO save name in queue
//        Chapter forwardedChapter = chapterRepository.findByItemIdAndDataVersionId(record.getChapterId(), botConfig.getCurrentVersion().getId()).orElseThrow();
        Optional<Paragraph> forwardedParagraph = paragraphRepository.findById(record.getChapterId());
        Optional<TGUser> tgUser = tgUserRepository.findByExternalId(record.getUserId());

        String dealName = forwardedParagraph.map(Paragraph::getNote).orElse("UNKNOWN");
        AddLeadComplex addLeadComplex = AddLeadComplex.builder()
                .name(dealName)
                .customFieldValues((tgUser.isPresent() && tgUser.get().getStartArg() != null) ?
                        List.of(MainCustomFieldValue.builder()
                                .fieldId(Integer.valueOf(amoIntegration.getCredential(UTM_SOURCE_FIELD_ID.getSysName())))
                                .values(List.of(CustomFieldValue.builder()
                                        .value(tgUser.get().getStartArg())
                                        .build()))
                                .build()) :
                        null)
                .embedded(LeadEmbedded.builder()
                        .contacts(List.of(com.tenagrim.telegram.dto.integration.amocrm.Contact.builder()
                                .firstName(String.format("%s %s %s", contact.getFirstName(), contact.getLastName(), user.getUserName()))
                                .customFieldValues(List.of(ContactCustomFieldValues.builder()
                                        .fieldCode("PHONE")
                                        .values(List.of(CustomFieldValue.builder()
                                                .enumCode("WORK")
                                                .value(contact.getPhoneNumber())
                                                .build()))
                                        .build()))
                                .build()))
                        .build())
                .build();

        sendLead(addLeadComplex, amoIntegration,
                success->{
                    System.out.println(success);
                },
                failure -> {
                    if (failure instanceof WebClientResponseException.Unauthorized) {
                        refreshAmoToken(botConfig, amoIntegration);
                        System.out.println("AMOCRM TOKEN REFRESHED SUCCESSFULLY");
                        sendLead(addLeadComplex, amoIntegration, System.out::println, System.out::println);
                    }else{
                        System.out.println(failure);
                    }
                });

    }

    private void sendLead(AddLeadComplex body, Integration amoIntegration, Consumer<? super String> onSuccess, Consumer<? super Throwable> onFailure) {
        String apiUrl = amoIntegration.getcredentialsMap().get(API_URL.getSysName());
        String authToken = amoIntegration.getcredentialsMap().get(JWT_TOKEN.getSysName());
        WebClient
                .create(apiUrl + AMO_ADD_COMPLEX_LEADS.getValue())
                .post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .body(Mono.just(List.of(body)), new ParameterizedTypeReference<>() {
                })
                .retrieve()
                .bodyToMono(String.class) // TODO map answer
                .subscribe(onSuccess, onFailure);
    }


    private void refreshAmoToken(BotConfig botConfig, Integration amoIntegration) {
        var request = tokenRequestFromIntegration(amoIntegration, AmoCrmGrantType.REFRESH_TOKEN);
        String apiUrl = amoIntegration.getCredential(API_URL.getSysName());
        WebClient
                .create(apiUrl + ACCESS_TOKEN.getValue())
                .post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), AmoCrmGrantType.class)
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .subscribe(response -> {
                            Integration integration = botConfig.getIntegrations().stream().filter(i -> i.getId().equals(amoIntegration.getId())).findFirst().orElseThrow();
                            updateCredential(integration, REFRESH_TOKEN.getSysName(), response.getRefreshToken());
                            updateCredential(integration, JWT_TOKEN.getSysName(), response.getAccessToken());
                            botConfigRepository.save(botConfig);
                        },
                        failure -> {
                            System.out.println(failure);
                        });
    }

    private RefreshAccessTokenRequest tokenRequestFromIntegration(Integration integration, AmoCrmGrantType grantType) {
        return RefreshAccessTokenRequest.builder()
                .clientId(integration.getCredential(CLIENT_ID.getSysName()))
                .clientSecret(integration.getCredential(CLIENT_SECRET.getSysName()))
                .grantType(grantType.getValue())
                .refreshToken(integration.getCredential(REFRESH_TOKEN.getSysName()))
                .redirectUri(integration.getCredential(REDIRECT_URI.getSysName()))
                .build();
    }

    public void updateCredential(Integration integration, String key, String value) {
        var credential = integration.getCredentials().stream()
                .filter(c -> c.getType().getSysName().equals(key))
                .findFirst()
                .orElseThrow(NotFoundException::new);
        credential.setValue(value);
        integration.clearCache();
    }
}
