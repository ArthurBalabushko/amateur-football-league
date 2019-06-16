package com.itacademy.database.repository;

import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class LandlordDaoTest extends BaseCase {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Test
    public void save() {
        Role role = roleRepository.findByName("landlord").orElse(null);

        Landlord landlord = new Landlord("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role);

        landlordRepository.save(landlord);

        assertNotNull(landlord.getId());
    }

    @Test
    public void findById() {
        Role role = roleRepository.findByName("landlord").orElse(null);

        Landlord landlord = new Landlord("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role);

        landlordRepository.save(landlord);

        Landlord resultLandlord = landlordRepository.findById(landlord.getId()).orElse(null);

        assertNotNull("Entity is null", resultLandlord);
    }

    @Test
    public void findByEmail() {
        Landlord resultLandlord = landlordRepository.findByEmail("kk@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultLandlord);
    }
}