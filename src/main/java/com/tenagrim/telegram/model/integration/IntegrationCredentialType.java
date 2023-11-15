package com.tenagrim.telegram.model.integration;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "INTEGRATION_CREDENTIAL_TYPE")
public class IntegrationCredentialType {
    @Id
    Long id;

    @Column(name = "SYSNAME")
    String sysName;

    @Column(name = "DESCRIPTION")
    String description;
}
