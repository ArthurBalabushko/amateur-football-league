package com.itacademy.database.repository;

import com.itacademy.database.entity.Sponsor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class SponsorDaoTest extends BaseCase {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Test
    public void save() {
        Sponsor sponsor = sponsorRepository.save(Sponsor.builder().name("test").build());

        assertNotNull(sponsor.getId());
    }

    @Test
    public void findById() {
        Sponsor sponsor = Sponsor.builder().name("test2").build();

        sponsorRepository.save(sponsor);

        Sponsor resultSponsor = sponsorRepository.findById(sponsor.getId()).orElse(null);

        assertNotNull("Entity is null", resultSponsor);
    }
}
