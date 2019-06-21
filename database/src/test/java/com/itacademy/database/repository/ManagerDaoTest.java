package com.itacademy.database.repository;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ManagerDaoTest extends BaseCase {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void save() {
        Role role = roleRepository.findByName("manager").orElse(null);

        Manager manager = new Manager("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role);

        managerRepository.save(manager);

        assertNotNull(manager.getId());
    }

    @Test
    public void findById() {
        Role role = roleRepository.findByName("manager").orElse(null);

        Manager manager = new Manager("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role);

        managerRepository.save(manager);

        Manager resultManager = managerRepository.findById(manager.getId()).orElse(null);

        assertNotNull("Entity is null", resultManager);
    }

    @Test
    public void findByEmail() {
        Manager resultManager = managerRepository.findByEmail("pp@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultManager);
    }
}