package com.tenagrim.telegram.model.integration;

import com.tenagrim.telegram.exception.NotFoundException;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "INTEGRATION")
public class Integration {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "INTEGRATION_ID", referencedColumnName = "ID")
    Set<IntegrationCredential> credentials;

    @Column(name = "INTEGRATION_TYPE_ID")
    private Long typeId;

    @Transient
    private Map<String, String> credentialsMapCache; // TODO: should not be in entity class

    public Map<String, String> getcredentialsMap(){ // TODO: should not be in entity class
        if (credentialsMapCache == null || credentialsMapCache.isEmpty()){
            credentialsMapCache = credentials.stream()
                    .collect(Collectors.toMap(el-> el.getType().sysName, IntegrationCredential::getValue));
        }
        return credentialsMapCache;
    }
    public void clearCache(){
        credentialsMapCache.clear();
    }

    public String getCredential(String key){
        if (!getcredentialsMap().containsKey(key)){
            throw new NotFoundException(String.format("Credential %s was not found for integration", key));
        }
        return getcredentialsMap().get(key);
    }

    public Long getId() {
        return id;
    }

    public Set<IntegrationCredential> getCredentials() {
        return credentials;
    }

    public Long getTypeId() {
        return typeId;
    }
}
