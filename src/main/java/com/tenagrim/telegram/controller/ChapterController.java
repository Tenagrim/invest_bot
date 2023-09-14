package com.tenagrim.telegram.controller;

import com.tenagrim.telegram.controller.base.SecuredRestController;
import com.tenagrim.telegram.dto.ChapterRequest;
import com.tenagrim.telegram.dto.SaveChaptersRequest;
import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
//@Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
//@SecurityRequirement(name = "bearerAuth")
public class ChapterController implements SecuredRestController {

    private final ChapterRepository chapterRepository;

    @GetMapping("/{versionId}")
    public List<Chapter> getChapters(@PathVariable Long versionId){
        return chapterRepository.findAllByDataVersionId(versionId);
    };

    @PostMapping("/get-chapter")
    public Chapter getChapterById(@RequestBody ChapterRequest request){
        return chapterRepository.findByItemIdAndDataVersionId(request.getItemId(), request.getVersionId()).
                orElseThrow(NotFoundException::new);
    };

    @PostMapping("/save-chapters")
    public List<Chapter> saveChapters (@RequestBody SaveChaptersRequest request){

        request.getChapters().forEach(c-> c.getChapterButtons().forEach(cb -> cb.setChapter(c)));

        return chapterRepository.saveAll(request.getChapters());
    }

    @DeleteMapping
    public ResponseEntity deleteChapters (@RequestBody ChapterRequest request){
        chapterRepository.deleteByItemIdAndDataVersionId(request.getItemId(), request.getVersionId());
        return ResponseEntity.ok().build();
    }

}
