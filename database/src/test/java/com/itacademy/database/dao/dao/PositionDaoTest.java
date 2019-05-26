package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.PositionDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Position;
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

public class PositionDaoTest {

    private PositionDao positionDao = PositionDao.getInstance();
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

        Position position = positionDao.save(session, Position.builder().name("test").build());

        session.getTransaction().commit();

        assertNotNull(position.getId());
    }

    @Test
    public void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer positionId = 1;
        Optional<Position> resultPosition = positionDao.findById(session, positionId);

        session.getTransaction().commit();

        Position expectedPosition = Position.builder()
                .id(1)
                .name("goalkeeper")
                .build();

        if (resultPosition.isPresent()) {
            assertEquals(expectedPosition, resultPosition.get());
            assertThat(resultPosition.get().getName(), equalTo("goalkeeper"));
            assertThat(resultPosition.get().getPlayers().size(), equalTo(2));
        }
    }
}
