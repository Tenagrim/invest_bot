package com.tenagrim.telegram.repository;

import com.tenagrim.telegram.model.chapter.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    void deleteAllByIdIn(List<Long> ids);

    void deleteByItemIdAndDataVersionId(Long itemId,Long dataVersionId);

    Optional<Chapter> findByItemIdAndDataVersionId(Long itemId,Long dataVersionId);

    List<Chapter> findAllByDataVersionId(Long dataVersionId);
    List<Chapter> findAllByItemIdAndDataVersionId(Long itemId,Long dataVersionId);


}
