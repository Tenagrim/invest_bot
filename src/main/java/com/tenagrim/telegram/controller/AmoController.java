package com.tenagrim.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integration/amo")
@RequiredArgsConstructor
public class AmoController {

    @PostMapping("/auth")
    public ResponseEntity<String> login(@RequestBody String request) {
        System.out.println(request);
        return ResponseEntity.ok("well done");
    }

    @GetMapping("/auth")
    public ResponseEntity<String> getlogin() {
        return ResponseEntity.ok("well done");
    }

}
