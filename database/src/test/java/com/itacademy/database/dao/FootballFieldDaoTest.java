package com.itacademy.database.dao;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Location;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class FootballFieldDaoTest extends BaseTest {

    private FootballFieldDao footballFieldDao = FootballFieldDao.getInstance();

    @Test
    public void save() {
        Landlord landlord = LandlordDao.getInstance().findByEmail("kk@mail.ru").orElse(null);

        FootballField footballField = FootballField.builder()
                .name("test")
                .phoneNumber("+375 234 56 78")
                .location(Location.builder()
                        .city("Minsk")
                        .street("Green")
                        .build())
                .landlord(landlord)
                .build();

        assertNotNull(footballFieldDao.save(footballField));
    }

    @Test
    public void findById() {
        Landlord landlord = LandlordDao.getInstance().findByEmail("kk@mail.ru").orElse(null);

        FootballField footballField = FootballField.builder()
                .name("test2")
                .phoneNumber("+375 234 56 78")
                .location(Location.builder()
                        .city("Minsk")
                        .street("Green")
                        .build())
                .landlord(landlord)
                .build();

        footballFieldDao.save(footballField);

        FootballField fieldResult = footballFieldDao.findById(footballField.getId()).orElse(null);

        assertNotNull("Entity is null", fieldResult);
    }

    @Test
    public void findByName() {
        FootballField footballField = footballFieldDao.findByName("СК Динамо").orElse(null);

        assertNotNull("Entity is null", footballField);
    }
}
