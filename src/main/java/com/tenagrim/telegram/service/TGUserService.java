package com.tenagrim.telegram.service;

import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.mappers.UserMapper;
import com.tenagrim.telegram.model.TGContact;
import com.tenagrim.telegram.model.TGUser;
import com.tenagrim.telegram.repository.TGUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TGUserService {
    private final TGUserRepository userRepository;
    private final UserMapper userMapper;

    public TGUser getByExternalId(Long id){
        return userRepository.findByExternalId(id)
                .orElseThrow(NotFoundException::new);
    }

    public TGUser saveIfNotSaved(User user){
        Optional<TGUser> tgUser = userRepository.findByExternalId(user.getId());
        return tgUser.orElseGet(() -> saveUser(user));
    }

    public TGUser addContact(User user, Contact contact){
        TGUser tgUser = saveIfNotSaved(user);
        TGContact tgContact = userMapper.map(contact);
        tgContact.setUser(tgUser);
        tgUser.getContacts().add(tgContact);
        return userRepository.save(tgUser);
    }

    public TGUser saveUser(User user){
        TGUser tgUser = userMapper.map(user);
        return userRepository.save(tgUser);
    }
}
