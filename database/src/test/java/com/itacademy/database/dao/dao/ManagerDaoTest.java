package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.ManagerDao;
import com.itacademy.database.dao.RoleDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Role;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ManagerDaoTest {

    private ManagerDao managerDao = ManagerDao.getInstance();
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataHelper.getInstance().importTestData(sessionFactory);
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

    @Test
    public void save() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role role = RoleDao.getInstance().findById(session, 2).orElse(null);

        Manager manager = new Manager("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role);

        managerDao.save(session, manager);

        session.getTransaction().commit();

        assertNotNull(manager.getId());
    }

    @Test
    public void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long managerId = 1L;
        Optional<Manager> resultManager = managerDao.findById(session, managerId);
        Integer roleId = 2;
        Optional<Role> role = RoleDao.getInstance().findById(session, roleId);

        session.getTransaction().commit();

        if (resultManager.isPresent() && role.isPresent()) {

            Manager expectedManager = new Manager("Петя", "Петров",
                    LocalDate.of(1992, 7, 10), "+375 33 378 12 34",
                    "pp@mail.ru", "1234", role.get());

            assertEquals(expectedManager.getEmail(), resultManager.get().getEmail());
            assertThat(resultManager.get().getTeam().getName(), equalTo("FC Arsenal"));
        }
    }
}