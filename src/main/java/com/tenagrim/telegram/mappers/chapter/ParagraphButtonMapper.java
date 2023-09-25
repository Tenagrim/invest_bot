package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.chapter.ParagraphButton;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ParagraphButtonMapper {
    @Mapping(target = "id", ignore = true)
    public abstract ParagraphButton map(ParagraphButton paragraphButton);

    @Named("mapParagraphButtons")
    public abstract Set<ParagraphButton> map(Set<ParagraphButton> paragraphButton);
}
