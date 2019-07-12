package com.itacademy.service.mapper;

import com.itacademy.database.dto.CreateLandlordDto;
import com.itacademy.database.dto.ViewLandlordDto;
import com.itacademy.database.entity.Landlord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface LandlordMapper {

    @Mappings({
            @Mapping(target = "birthDay", source = "landlord.birthDay", dateFormat = "dd MMMM yyyy")})
    ViewLandlordDto landlordTolandlordDto(Landlord landlord);

    @Mappings({
            @Mapping(target = "birthDay", qualifiedByName = "date")})
    Landlord createLandlordDtoToLandlord(CreateLandlordDto createLandlordDto);

    @Mappings({
            @Mapping(target = "birthDay", qualifiedByName = "date")})
    Landlord landlordDtoToLandlord(ViewLandlordDto landlordDto);

    @Named("date")
    default LocalDate format(String birthDay) {
        return LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }
}
