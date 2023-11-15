package com.tenagrim.telegram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IntegrationCredentialType {
    JWT_TOKEN(1L, "JWT_TOKEN", ""),
    REFRESH_TOKEN(2L, "REFRESH_TOKEN", ""),
    API_URL(3L, "API_URL", ""),
    CLIENT_ID(4L, "CLIENT_ID", ""),
    CLIENT_SECRET(5L, "CLIENT_SECRET", "");

    private final Long id;
    private final String sysName;
    private final String description;
}
