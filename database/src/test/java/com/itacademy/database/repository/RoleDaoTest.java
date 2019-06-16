package com.itacademy.database.repository;

import com.itacademy.database.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class RoleDaoTest extends BaseCase {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void save() {
        Role role = roleRepository.save(Role.builder().name("test").build());

        assertNotNull(role.getId());
    }

    @Test
    public void findById() {
        Role role = Role.builder().name("test2").build();
        roleRepository.save(role);
        Role resultRole = roleRepository.findById(role.getId()).orElse(null);

        assertNotNull("Entity is null", resultRole);
    }

    @Test
    public void findByName() {
        Role role = roleRepository.findByName("manager").orElse(null);

        assertNotNull("Entity is null", role);
    }
}