package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.integration.IntegrationCredentialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationCredentialTypeRepository extends JpaRepository<IntegrationCredentialType, Long> {

    IntegrationCredentialType findBySysName(String sysName);
}
