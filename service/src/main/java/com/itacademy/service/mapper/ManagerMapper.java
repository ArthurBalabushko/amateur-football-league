package com.itacademy.service.mapper;

import com.itacademy.database.dto.ViewManagerDto;
import com.itacademy.database.entity.Manager;
import com.itacademy.service.formatter.DateFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mappings({
            @Mapping(target = "birthDay", source = "manager.birthDay", dateFormat = "dd MMMM yyyy")})
    ViewManagerDto managerToManagerDto(Manager manager);

    @Mappings({
            @Mapping(target = "birthDay", qualifiedBy = DateTimeFormat.class)})
    Manager managerDtoToManager(ViewManagerDto managerDto);

    @DateTimeFormat
    default LocalDate format(String date) {
        return new DateFormat().format(date);
    }
}
