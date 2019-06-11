package com.itacademy.database.dao;

import com.itacademy.database.entity.Position;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PositionDaoTest extends BaseTest {

    private PositionDao positionDao = PositionDao.getInstance();

    @Test
    public void save() {
        Integer positionId = positionDao.save(Position.builder().name("test").build());

        assertNotNull(positionId);
    }

    @Test
    public void findById() {
        Position position = Position.builder().name("test2").build();
        positionDao.save(position);
        Position resultPosition = positionDao.findById(position.getId()).orElse(null);

        assertNotNull("Entity is null", resultPosition);
    }

    @Test
    public void findByName() {
        Position position = positionDao.findByName("goalkeeper").orElse(null);

        assertNotNull("Entity is null", position);
    }
}
