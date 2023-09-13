package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.TGUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TGUserRepository extends JpaRepository<TGUser, Long> {

    Optional<TGUser> findByExternalId(Long externalId);
}
