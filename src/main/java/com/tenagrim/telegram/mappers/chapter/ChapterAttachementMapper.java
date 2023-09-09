package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.ChapterAttachement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ChapterAttachementMapper {

    @Mapping(target = "id", ignore = true)
    public abstract ChapterAttachement map(ChapterAttachement chapter);

    @Named("mapChapterAttachements")
    public abstract Set<ChapterAttachement> map(Set<ChapterAttachement> chapters);
}
