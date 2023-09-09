package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.DataVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        ChapterButtonMapper.class,
        ChapterAttachementMapper.class,
        NodePositionMapper.class
})
public abstract class ChapterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chapterButtons", qualifiedByName = "mapChapterButtons")
    @Mapping(target = "chapterAttachements", qualifiedByName = "mapChapterAttachements")
    abstract Chapter mapInternal(Chapter chapter);
    public Chapter map(Chapter chapter){
        Chapter result = mapInternal(chapter);
        if(result.getNodePosition() != null){
            result.getNodePosition().setChapter(result);
        }
        result.getChapterButtons().forEach(c->c.setChapter(result));
        result.getChapterAttachements().forEach(c->c.setChapter(result));
        return result;
    }

    public List<Chapter> map(List<Chapter> chapters){
        return chapters.stream().map(this::map).collect(Collectors.toList());
    }

    public List<Chapter> map(List<Chapter> chapters, DataVersion dataVersion){
        List<Chapter> result = map(chapters);
        result.forEach(c->c.setDataVersionId(dataVersion.getId()));
        return result;
    }
}
