package com.tenagrim.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class CacheController {

    private final CacheManager cacheManager;

    @PostMapping("/clear-all")
    public ResponseEntity clearAll(){
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
        return ResponseEntity.ok().build();
    }
}
