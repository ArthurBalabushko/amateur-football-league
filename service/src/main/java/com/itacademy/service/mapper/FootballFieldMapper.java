package com.itacademy.service.mapper;

import com.itacademy.database.dto.CreateFootballFieldDto;
import com.itacademy.database.dto.ViewFootballFieldDto;
import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = LandlordMapper.class)
public interface FootballFieldMapper {

    ViewFootballFieldDto fieldToFieldDto(FootballField field);

    FootballField fieldDtoToField(ViewFootballFieldDto fieldDto);

    @Mappings({
            @Mapping(target = "location", qualifiedByName = "location")})
    FootballField createFieldDtoToField(CreateFootballFieldDto fieldDto);

    @Named("location")
    default Location format(String city, String address) {
        return Location.builder().city(city).street(address).build();
    }
}
