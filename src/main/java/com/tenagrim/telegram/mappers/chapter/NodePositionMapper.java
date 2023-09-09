package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.mappers.chapter.ChapterAttachementMapper;
import com.tenagrim.telegram.mappers.chapter.ChapterButtonMapper;
import com.tenagrim.telegram.model.NodePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NodePositionMapper {

    @Mapping(target = "id", ignore = true)
    public abstract NodePosition map(NodePosition nodePosition);

    public abstract List<NodePosition> map(List<NodePosition> nodePositions);
}
