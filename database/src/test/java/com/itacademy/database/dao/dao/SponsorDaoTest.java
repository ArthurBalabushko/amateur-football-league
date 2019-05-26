package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.SponsorDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Sponsor;
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

public class SponsorDaoTest {

    private SponsorDao sponsorDao = SponsorDao.getInstance();
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

        Sponsor sponsor = sponsorDao.save(session, Sponsor.builder().name("test").build());

        session.getTransaction().commit();

        assertNotNull(sponsor.getId());
    }

    @Test
    public void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long sponsorId = 1L;
        Optional<Sponsor> resultSponsor = sponsorDao.findById(session, sponsorId);

        session.getTransaction().commit();

        Sponsor expectedSponsor = Sponsor.builder()
                .id(1L)
                .name("Etihad Airways")
                .build();

        if (resultSponsor.isPresent()) {
            assertEquals(expectedSponsor, resultSponsor.get());
            assertThat(resultSponsor.get().getName(), equalTo("Etihad Airways"));
        }
    }
}
