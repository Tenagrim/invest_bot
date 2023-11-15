package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddLeadComplex {
    private String name;
    @JsonProperty("_embedded")
    private LeadEmbedded embedded;
}
