package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddLeadComplex {
    private String name;

    @JsonProperty("custom_fields_values")
    private List<MainCustomFieldValue> customFieldValues;

    @JsonProperty("_embedded")
    private LeadEmbedded embedded;
}
