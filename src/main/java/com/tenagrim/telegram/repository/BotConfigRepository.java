package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.BotConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotConfigRepository extends JpaRepository<BotConfig, Long> {
    Optional<BotConfig> findBySysName(String sysName);
}
