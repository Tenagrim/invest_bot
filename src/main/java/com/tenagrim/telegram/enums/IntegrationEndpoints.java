package com.tenagrim.telegram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntegrationEndpoints {
    AMO_ADD_COMPLEX_LEADS("/api/v4/leads/complex");

    private final String value;
}
