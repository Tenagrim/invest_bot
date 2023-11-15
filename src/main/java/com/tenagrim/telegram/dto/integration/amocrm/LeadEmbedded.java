package com.tenagrim.telegram.dto.integration.amocrm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LeadEmbedded {
    List<Contact> contacts;
}
