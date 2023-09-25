package com.tenagrim.telegram.controller;

import com.tenagrim.telegram.controller.base.SecuredRestController;
import com.tenagrim.telegram.dto.ChapterRequest;
import com.tenagrim.telegram.dto.SaveChaptersRequest;
import com.tenagrim.telegram.model.AppUser;
import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
//@Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
public class ChapterController implements SecuredRestController {

    private final ChapterService chapterService;

    @GetMapping("/{versionId}")
    public List<Chapter> getChapters(@PathVariable Long versionId){
        return chapterService.findAllByDataVersionId(versionId);
    };

    @PostMapping("/get-chapter")
    public Chapter getChapterById(@RequestBody ChapterRequest request){
        return chapterService.findByItemIdAndDataVersionId(request.getItemId(), request.getVersionId());
    };

    @PostMapping("/save-chapters")
    public List<Chapter> saveChapters (@RequestBody SaveChaptersRequest request){

        SecurityContext context = SecurityContextHolder.getContext();
        return chapterService.saveChapters(request.getChapters(), (AppUser) context.getAuthentication().getPrincipal());
    }

    @DeleteMapping
    public ResponseEntity deleteChapters (@RequestBody ChapterRequest request){
        chapterService.deleteByItemIdAndDataVersionId(request.getItemId(), request.getVersionId());
        return ResponseEntity.ok().build();
    }

}
