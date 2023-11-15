package com.tenagrim.telegram.model.integration;

import com.tenagrim.telegram.model.config.BotConfigProperty;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
