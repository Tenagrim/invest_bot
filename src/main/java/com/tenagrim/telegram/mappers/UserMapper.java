package com.tenagrim.telegram.mappers;

import com.tenagrim.telegram.dto.AppUserView;
import com.tenagrim.telegram.model.AppUser;
import com.tenagrim.telegram.model.TGContact;
import com.tenagrim.telegram.model.TGUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.User;

@Mapper(componentModel ="spring")
public abstract class UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId",source = "id")
    public abstract TGUser map(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactTypeId", constant = "1L")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "value", source = "phoneNumber")
    public abstract TGContact map(Contact contact);

    public abstract AppUserView map(AppUser user);
}
