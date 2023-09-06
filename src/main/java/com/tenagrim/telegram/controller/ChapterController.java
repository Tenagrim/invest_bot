package com.tenagrim.telegram.controller;

import com.tenagrim.telegram.dto.SaveChaptersRequest;
import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.ChapterButton;
import com.tenagrim.telegram.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterRepository chapterRepository;

    @GetMapping
    public List<Chapter> getChapters(){
        return chapterRepository.findAll();
    };

    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable Long id){
        return chapterRepository.findById(id).orElseThrow(NotFoundException::new);
    };

    @PostMapping
    public List<Chapter> saveChapters (@RequestBody SaveChaptersRequest request){

        request.getChapters().forEach(c-> c.getChapterButtons().forEach(cb -> cb.setChapter(c)));

        return chapterRepository.saveAll(request.getChapters());
    }

    @DeleteMapping
    public ResponseEntity deleteChapters (@RequestBody List<Long> chapterIds){
        chapterRepository.deleteAllByIdIn(chapterIds);
        return ResponseEntity.ok().build();
    }

}
