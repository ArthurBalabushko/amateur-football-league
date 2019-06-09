package com.itacademy.database.dao;

import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Role;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class LandlordDaoTest extends BaseTest {

    private LandlordDao landlordDao = LandlordDao.getInstance();

    @Test
    public void save() {
        Role role = RoleDao.getInstance().findByName("landlord").orElse(null);

        Landlord landlord = new Landlord("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role);

        landlordDao.save(landlord);

        assertNotNull(landlord.getId());
    }

    @Test
    public void findById() {
        Role role = RoleDao.getInstance().findByName("landlord").orElse(null);

        Landlord landlord = new Landlord("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role);

        landlordDao.save(landlord);

        Landlord resultLandlord = landlordDao.findById(landlord.getId()).orElse(null);

        assertNotNull("Entity is null", resultLandlord);
    }

    @Test
    public void findByEmail() {
        Landlord resultLandlord = landlordDao.findByEmail("kk@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultLandlord);
    }
}