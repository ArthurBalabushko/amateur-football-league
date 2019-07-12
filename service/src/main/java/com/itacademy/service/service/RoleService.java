package com.itacademy.service.service;

import com.itacademy.database.dto.ViewRoleDto;
import com.itacademy.database.entity.Role;
import com.itacademy.database.repository.RoleRepository;
import com.itacademy.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<ViewRoleDto> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().iterator().forEachRemaining(roles::add);

        return roles.stream().map(it -> ViewRoleDto.builder()
                .id(it.getId())
                .name(it.getName())
                .build()).collect(Collectors.toList());
    }

    public ViewRoleDto findById(Integer roleId) {
        ViewRoleDto roleDto = null;

        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            roleDto = roleMapper.roleToRoleDto(role.get());
        }

        return roleDto;
    }

    public ViewRoleDto findByName(String roleName) {
        ViewRoleDto roleDto = null;

        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isPresent()) {
            roleDto = roleMapper.roleToRoleDto(role.get());
        }

        return roleDto;
    }
}
