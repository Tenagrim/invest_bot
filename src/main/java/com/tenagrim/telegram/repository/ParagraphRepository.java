package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.chapter.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {
}
