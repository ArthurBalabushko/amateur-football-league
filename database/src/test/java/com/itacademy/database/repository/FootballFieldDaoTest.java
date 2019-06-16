package com.itacademy.database.repository;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Location;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class FootballFieldDaoTest extends BaseCase {

    @Autowired
    private FootballFieldRepository footballFieldRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Test
    public void save() {
        Landlord landlord = landlordRepository.findByEmail("kk@mail.ru").orElse(null);

        FootballField footballField = FootballField.builder()
                .name("test")
                .phoneNumber("+375 234 56 78")
                .location(Location.builder()
                        .city("Minsk")
                        .street("Green")
                        .build())
                .landlord(landlord)
                .build();

        footballFieldRepository.save(footballField);

        assertNotNull(footballField.getId());
    }

    @Test
    public void findById() {
        Landlord landlord = landlordRepository.findByEmail("kk@mail.ru").orElse(null);

        FootballField footballField = FootballField.builder()
                .name("test2")
                .phoneNumber("+375 234 56 78")
                .location(Location.builder()
                        .city("Minsk")
                        .street("Green")
                        .build())
                .landlord(landlord)
                .build();

        footballFieldRepository.save(footballField);

        FootballField fieldResult = footballFieldRepository.findById(footballField.getId()).orElse(null);

        assertNotNull("Entity is null", fieldResult);
    }

    @Test
    public void findByName() {
        FootballField footballField = footballFieldRepository.findByName("СК Динамо").orElse(null);

        assertNotNull("Entity is null", footballField);
    }
}
