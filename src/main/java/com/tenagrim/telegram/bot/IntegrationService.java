package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.dto.integration.amocrm.AddLeadComplex;
import com.tenagrim.telegram.dto.integration.amocrm.ContactCustomFieldValues;
import com.tenagrim.telegram.dto.integration.amocrm.CustomFieldValue;
import com.tenagrim.telegram.dto.integration.amocrm.LeadEmbedded;
import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.model.config.BotConfig;
import com.tenagrim.telegram.model.integration.Integration;
import com.tenagrim.telegram.model.integration.IntegrationQueueRecord;
import com.tenagrim.telegram.model.integration.IntegrationTrigger;
import com.tenagrim.telegram.repository.ChapterRepository;
import com.tenagrim.telegram.repository.IntegrationQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static com.tenagrim.telegram.enums.IntegrationCredentialType.API_URL;
import static com.tenagrim.telegram.enums.IntegrationCredentialType.JWT_TOKEN;
import static com.tenagrim.telegram.enums.IntegrationEndpoints.AMO_ADD_COMPLEX_LEADS;
import static com.tenagrim.telegram.enums.IntegrationType.AMO_CRM;

@Service
@RequiredArgsConstructor
public class IntegrationService {

    private final IntegrationQueueRepository queueRepository;
    private final ChapterRepository chapterRepository;

    public void pushRecords(BotConfig botConfig, Chapter chapter, Long userId) {
        for (IntegrationTrigger t : chapter.getIntegrationTriggers()) {
            if (botConfig.getIntegrations().stream().allMatch(i -> Objects.equals(i.getTypeId(), t.getIntegrationTypeId()))) {
                queueRepository.save(IntegrationQueueRecord.builder()
                        .userId(userId)
                        .chapterId(chapter.getItemId())
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
        String apiUrl = amoIntegration.getcredentialsMap().get(API_URL.getSysName());
        String authToken = amoIntegration.getcredentialsMap().get(JWT_TOKEN.getSysName());

        // TODO save name in queue
        Chapter forwardedChapter = chapterRepository.findByItemIdAndDataVersionId(record.getChapterId(), botConfig.getCurrentVersion().getId()).orElseThrow();

        WebClient
                .create(apiUrl + AMO_ADD_COMPLEX_LEADS.getValue())
                .post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .body(Mono.just(List.of(AddLeadComplex.builder()
                        .name(forwardedChapter.getNote())
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
                        .build())), new ParameterizedTypeReference<>() {
                })
                .retrieve()
                .bodyToMono(String.class) // TODO map answer
                .subscribe(
                        System.out::println,
                        failure -> {
//                            failure.ge
                        });

    }
}
