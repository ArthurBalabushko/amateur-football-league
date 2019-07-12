package com.itacademy.service.mapper;

import com.itacademy.database.dto.ViewRoleDto;
import com.itacademy.database.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    ViewRoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(ViewRoleDto roleDto);
}
