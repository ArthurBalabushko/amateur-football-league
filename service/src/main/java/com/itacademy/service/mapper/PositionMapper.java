package com.itacademy.service.mapper;

import com.itacademy.database.dto.ViewPositionDto;
import com.itacademy.database.entity.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    ViewPositionDto positionToPositionDto(Position position);

    Position positionDtoToPosition(ViewPositionDto positionDto);
}
