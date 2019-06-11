package com.itacademy.database.dao;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.RequestOnField;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;

public class RequestOnFieldDaoTest extends BaseTest {

    private RequestOnFieldDao requestOnFieldDao = RequestOnFieldDao.getInstance();

    @Test
    public void save() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        FootballField footballfield = FootballFieldDao.getInstance().findByName("СК Динамо").orElse(null);

        RequestOnField requestOnField = RequestOnField.builder()
                .date(LocalDate.now())
                .footballField(footballfield)
                .day(LocalDate.of(2019, 7, 21))
                .timeStart(LocalTime.of(19, 30))
                .timeFinish(LocalTime.of(21, 30))
                .team(team)
                .status(Status.WAITING)
                .build();

        requestOnFieldDao.save(requestOnField);

        assertNotNull(requestOnField);
    }

    @Test
    public void findById() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        FootballField footballfield = FootballFieldDao.getInstance().findByName("СК Динамо").orElse(null);

        RequestOnField requestOnField = RequestOnField.builder()
                .date(LocalDate.now())
                .footballField(footballfield)
                .day(LocalDate.of(2019, 07, 23))
                .timeStart(LocalTime.of(13, 30))
                .timeFinish(LocalTime.of(15, 30))
                .team(team)
                .status(Status.WAITING)
                .build();

        requestOnFieldDao.save(requestOnField);

        RequestOnField resultRequest = requestOnFieldDao.findById(requestOnField.getId()).orElse(null);

        assertNotNull("Entity is null", resultRequest);
    }
}