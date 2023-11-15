package com.tenagrim.telegram.dto.integration.amocrm.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tenagrim.telegram.enums.AmoCrmGrantType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshAccessTokenRequest {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("redirect_uri")
    private String redirectUri;
}
