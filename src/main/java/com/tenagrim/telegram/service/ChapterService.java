package com.tenagrim.telegram.service;

import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.AppUser;
import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;

    public List<Chapter> saveChapters(List<Chapter> chapters, AppUser principal){
        ZonedDateTime now = ZonedDateTime.now();
        chapters.forEach(c->{
            c.getChapterParagraphs().forEach(cp->{
                cp.setChapter(c);
                cp.getParagraphButtons().forEach(pb->{pb.setParagraph(cp);});
            });
            c.setUpdateUserId(principal.getId());
            c.setUpdateDate(now);
        });
        return chapterRepository.saveAll(chapters);
    }

    public List<Chapter> findAllByDataVersionId(Long versionId){
        return chapterRepository.findAllByDataVersionId(versionId);
    }

    public Chapter findByItemIdAndDataVersionId(Long itemId, Long dataVersionId){
        return chapterRepository.findByItemIdAndDataVersionId(itemId, dataVersionId).
                orElseThrow(NotFoundException::new);
    }

    public void deleteByItemIdAndDataVersionId(Long itemId,Long dataVersionId){
        chapterRepository.deleteByItemIdAndDataVersionId(itemId, dataVersionId);
    }

}
