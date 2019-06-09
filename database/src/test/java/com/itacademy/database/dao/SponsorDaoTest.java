package com.itacademy.database.dao;

import com.itacademy.database.entity.Sponsor;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SponsorDaoTest extends BaseTest {

    private SponsorDao sponsorDao = SponsorDao.getInstance();

    @Test
    public void save() {
        Long sponsorId = sponsorDao.save(Sponsor.builder().name("test").build());

        assertNotNull(sponsorId);
    }

    @Test
    public void findById() {
        Sponsor sponsor = Sponsor.builder().name("test2").build();

        sponsorDao.save(sponsor);

        Sponsor resultSponsor = sponsorDao.findById(sponsor.getId()).orElse(null);

        assertNotNull("Entity is null", resultSponsor);
    }
}
