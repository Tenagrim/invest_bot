package com.tenagrim.telegram.dto.integration.amocrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Contact {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("custom_fields_values")
    List<ContactCustomFieldValues> customFieldValues;
}
