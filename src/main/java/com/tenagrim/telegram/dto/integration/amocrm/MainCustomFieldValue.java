package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MainCustomFieldValue {
    @JsonProperty("field_id")
    private Integer fieldId;
    List<CustomFieldValue> values;
}
