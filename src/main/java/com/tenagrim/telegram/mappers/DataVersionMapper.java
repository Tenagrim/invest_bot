package com.tenagrim.telegram.mappers;

import com.tenagrim.telegram.model.DataVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DataVersionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    public abstract DataVersion map(DataVersion dataVersion);
}
