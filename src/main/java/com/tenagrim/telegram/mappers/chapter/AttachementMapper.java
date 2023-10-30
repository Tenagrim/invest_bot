package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.chapter.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AttachementMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Attachment map(Attachment chapter);

    @Named("mapChapterAttachements")
    public abstract Set<Attachment> map(Set<Attachment> chapters);
}
