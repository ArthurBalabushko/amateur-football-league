package com.itacademy.service.mapper;

import com.itacademy.database.dto.ViewUserDto;
import com.itacademy.database.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "birthDay", source = "user.birthDay", dateFormat = "dd MMMM yyyy")})
    ViewUserDto userToUserDto(User user);

    @Mappings({
            @Mapping(target = "birthDay", qualifiedByName = "date")})
    User userDtoToUser(ViewUserDto userDto);

    @Named("date")
    default LocalDate format(String birthDay) {
        return LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }
}
