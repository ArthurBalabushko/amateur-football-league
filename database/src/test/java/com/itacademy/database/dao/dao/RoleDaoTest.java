package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.RoleDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Role;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RoleDaoTest {

    private RoleDao roleDao = RoleDao.getInstance();
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

        Role role = roleDao.save(session, Role.builder().name("test").build());

        session.getTransaction().commit();

        assertNotNull(role.getId());
    }

    @Test
    public void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer roleId = 1;
        Optional<Role> resultRole = roleDao.findById(session, roleId);

        session.getTransaction().commit();

        Role expectedRole = Role.builder()
                .id(1)
                .name("player")
                .build();

        if (resultRole.isPresent()) {
            assertEquals(expectedRole, resultRole.get());
            assertThat(resultRole.get().getName(), equalTo("player"));
            assertThat(resultRole.get().getUsers().size(), equalTo(3));
        }
    }
}