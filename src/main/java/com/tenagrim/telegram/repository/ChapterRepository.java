package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    void deleteAllByIdIn(List<Long> ids);
}
