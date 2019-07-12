package com.itacademy.service.service;

import com.itacademy.database.dto.ViewUserDto;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ViewUserDto findById(Long id);

    User findByEmail(String email);

    void updateRole(Role role, Long id);
}
