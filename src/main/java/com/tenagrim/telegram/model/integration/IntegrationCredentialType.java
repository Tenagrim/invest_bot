package com.tenagrim.telegram.model.integration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTEGRATION_CREDENTIAL_TYPE")
public class IntegrationCredentialType {
    @Id
    Long id;

    @Column(name = "SYSNAME")
    String sysName;

    @Column(name = "DESCRIPTION")
    String description;
}
