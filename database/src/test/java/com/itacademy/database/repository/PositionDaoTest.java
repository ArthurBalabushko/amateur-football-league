package com.itacademy.database.repository;

import com.itacademy.database.entity.Position;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class PositionDaoTest extends BaseCase {

    @Autowired
    private PositionRepository positionRepository;

    @Test
    public void save() {
        Position position = positionRepository.save(Position.builder().name("test").build());

        assertNotNull(position.getId());
    }

    @Test
    public void findById() {
        Position position = Position.builder().name("test2").build();
        positionRepository.save(position);
        Position resultPosition = positionRepository.findById(position.getId()).orElse(null);

        assertNotNull("Entity is null", resultPosition);
    }

    @Test
    public void findByName() {
        Position position = positionRepository.findByName("goalkeeper").orElse(null);

        assertNotNull("Entity is null", position);
    }
}
