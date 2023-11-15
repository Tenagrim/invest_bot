package com.tenagrim.telegram.model.integration;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "INTEGRATION_TRIGGER")
public class IntegrationTrigger {
    @Id
    private Long id;

    @Column(name = "INTEGRATION_TYPE_ID")
    private Long integrationTypeId;
}
