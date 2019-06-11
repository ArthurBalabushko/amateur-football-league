package com.itacademy.database.dao;

import com.itacademy.database.entity.Role;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RoleDaoTest extends BaseTest {

    private RoleDao roleDao = RoleDao.getInstance();

    @Test
    public void save() {
        Integer roleId = roleDao.save(Role.builder().name("test").build());

        assertNotNull(roleId);
    }

    @Test
    public void findById() {
        Role role = Role.builder().name("test2").build();
        roleDao.save(role);
        Role resultRole = roleDao.findById(role.getId()).orElse(null);

        assertNotNull("Entity is null", resultRole);
    }

    @Test
    public void findByName() {
        Role role = roleDao.findByName("manager").orElse(null);

        assertNotNull("Entity is null", role);
    }
}