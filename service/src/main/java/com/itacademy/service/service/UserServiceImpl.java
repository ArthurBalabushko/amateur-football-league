package com.itacademy.service.service;

import com.itacademy.database.dto.ViewUserDto;
import com.itacademy.database.entity.Role;
import com.itacademy.database.repository.UserRepository;
import com.itacademy.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ManagerService managerService;
    private final UserMapper userMapper;

    @Override
    public com.itacademy.database.entity.User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public ViewUserDto findById(Long id) {
        ViewUserDto userDto = null;

        Optional<com.itacademy.database.entity.User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userDto = userMapper.userToUserDto(user.get());
        }

        return userDto;
    }

    @Override
    public void updateRole(Role role, Long id) {
        userRepository.update(role, id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return Optional.of(email)
                .map(it -> userRepository.findByEmail(email)).get()
                .map(user -> User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole().getName())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
