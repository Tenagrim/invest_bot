package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContactCustomFieldValues {
    @JsonProperty("field_code")
    String fieldCode;

    List<CustomFieldValue> values;
}
