package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomFieldValue {
    @JsonProperty("enum_code")
    private String enumCode;
    private String value;
}
