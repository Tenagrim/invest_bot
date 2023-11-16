package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.chapter.Paragraph;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {
    @Override
    @Cacheable("paragraph")
    Optional<Paragraph> findById(Long aLong);
}
