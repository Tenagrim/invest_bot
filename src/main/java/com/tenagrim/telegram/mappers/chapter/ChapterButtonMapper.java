package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.ChapterButton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ChapterButtonMapper {
    @Mapping(target = "id", ignore = true)
    public abstract ChapterButton map(ChapterButton chapterButton);

    @Named("mapChapterButtons")
    public abstract Set<ChapterButton> map(Set<ChapterButton> chapterButton);
}
