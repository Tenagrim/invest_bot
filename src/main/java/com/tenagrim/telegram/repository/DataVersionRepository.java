package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.DataVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataVersionRepository extends JpaRepository<DataVersion, Long> {

    List<DataVersion> findAllByBotConfigVersionId(Long configVersionId);
}
