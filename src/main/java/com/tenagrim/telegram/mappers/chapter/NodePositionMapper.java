package com.tenagrim.telegram.mappers.chapter;

import com.tenagrim.telegram.model.chapter.NodePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NodePositionMapper {

    @Mapping(target = "id", ignore = true)
    public abstract NodePosition map(NodePosition nodePosition);

    public abstract List<NodePosition> map(List<NodePosition> nodePositions);
}
