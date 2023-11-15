package com.tenagrim.telegram.model.integration;

import com.tenagrim.telegram.model.config.BotConfigProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "INTEGRATION_CREDENTIAL")
public class IntegrationCredential {
    @Id
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INTEGRATION_CREDENTIAL_TYPE_ID", referencedColumnName = "ID")
    private IntegrationCredentialType type;
}
