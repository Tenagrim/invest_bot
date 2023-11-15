package com.tenagrim.telegram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IntegrationType {
    AMO_CRM(1L,"AMO_CRM", "amoCRM");
    private final Long id;
    private final String sysName;
    private final String description;
}
