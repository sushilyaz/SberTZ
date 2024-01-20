package org.example.mapper;

import org.example.dto.MobileCreateDTO;
import org.example.dto.MobileDTO;
import org.example.dto.MobileUpdateDTO;
import org.example.model.Mobile;
import org.mapstruct.*;

// BEGIN
@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class MobileMapper {
    public abstract Mobile map(MobileCreateDTO dto);

    public abstract MobileDTO map(Mobile model);
    public abstract Mobile map(MobileDTO category);

    public abstract void update(MobileUpdateDTO dto, @MappingTarget Mobile model);
}
