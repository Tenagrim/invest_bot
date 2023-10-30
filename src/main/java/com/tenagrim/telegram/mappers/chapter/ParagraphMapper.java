package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.chapter.Paragraph;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        ParagraphButtonMapper.class,
        AttachementMapper.class
})
public abstract class ParagraphMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paragraphButtons", qualifiedByName = "mapParagraphButtons")
    abstract Paragraph mapInternal(Paragraph paragraph);

    public Paragraph map (Paragraph paragraph){
        Paragraph result = mapInternal(paragraph);
        result.getParagraphButtons().forEach(b-> b.setParagraph(result));
        result.getParagraphButtons().forEach(b-> b.setParagraph(result));
        return result;
    }

    @Named("mapChapterParagraphs")
    public Set<Paragraph> map(Set<Paragraph> paragraphs){
        return paragraphs.stream().map(this::map).collect(Collectors.toSet());
    }
}
