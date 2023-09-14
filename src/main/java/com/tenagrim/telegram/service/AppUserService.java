package com.tenagrim.telegram.service;


import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.AppUser;
import com.tenagrim.telegram.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    }
}
