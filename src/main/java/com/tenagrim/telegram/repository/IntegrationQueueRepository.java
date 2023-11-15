package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.integration.IntegrationQueueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegrationQueueRepository extends JpaRepository<IntegrationQueueRecord, Long> {
    @Query("select distinct new IntegrationQueueRecord(id, userId, integrationTypeId, actual, chapterId)   from IntegrationQueueRecord where userId = :userId and actual = true")
    List<IntegrationQueueRecord> findDistinctActualByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("update IntegrationQueueRecord set actual = false  where userId = :userId")
    void  setInactiveByUserId(@Param("userId") Long userId);
}
