package com.itacademy.database.dao;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Role;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ManagerDaoTest extends BaseTest {

    private ManagerDao managerDao = ManagerDao.getInstance();

    @Test
    public void save() {
        Role role = RoleDao.getInstance().findByName("manager").orElse(null);

        Manager manager = new Manager("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role);

        managerDao.save(manager);

        assertNotNull(manager.getId());
    }

    @Test
    public void findById() {
        Role role = RoleDao.getInstance().findByName("manager").orElse(null);

        Manager manager = new Manager("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role);

        managerDao.save(manager);

        Manager resultManager = managerDao.findById(manager.getId()).orElse(null);

        assertNotNull("Entity is null", resultManager);
    }

    @Test
    public void findByEmail() {
        Manager resultManager = managerDao.findByEmail("pp@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultManager);
    }
}