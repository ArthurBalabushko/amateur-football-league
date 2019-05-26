package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.ManagerDao;
import com.itacademy.database.dao.TeamDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Team;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;


public class TeamDaoTest {

    private TeamDao teamDao = TeamDao.getInstance();
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

        Long managerId = 1L;
        Optional<Manager> manager = ManagerDao.getInstance().findById(session, managerId);

        if (manager.isPresent()) {
            Team team = Team.builder()
                    .name("test")
                    .manager(manager.get())
                    .build();

            teamDao.save(session, team);

            session.getTransaction().commit();

            assertNotNull(team.getId());
        }
    }

    @Test
    public void findById() {
    }
}
