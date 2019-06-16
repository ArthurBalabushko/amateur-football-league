package com.itacademy.database.repository;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.RequestOnField;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;

public class RequestOnFieldDaoTest extends BaseCase {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FootballFieldRepository footballFieldRepository;

    @Autowired
    private RequestOnFieldRepository requestOnFieldRepository;

    @Test
    public void save() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        FootballField footballfield = footballFieldRepository.findByName("СК Динамо").orElse(null);

        RequestOnField requestOnField = RequestOnField.builder()
                .date(LocalDate.now())
                .footballField(footballfield)
                .day(LocalDate.of(2019, 7, 21))
                .timeStart(LocalTime.of(19, 30))
                .timeFinish(LocalTime.of(21, 30))
                .team(team)
                .status(Status.WAITING)
                .build();

        requestOnFieldRepository.save(requestOnField);

        assertNotNull(requestOnField.getId());
    }

    @Test
    public void findById() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        FootballField footballfield = footballFieldRepository.findByName("СК Динамо").orElse(null);

        RequestOnField requestOnField = RequestOnField.builder()
                .date(LocalDate.now())
                .footballField(footballfield)
                .day(LocalDate.of(2019, 07, 23))
                .timeStart(LocalTime.of(13, 30))
                .timeFinish(LocalTime.of(15, 30))
                .team(team)
                .status(Status.WAITING)
                .build();

        requestOnFieldRepository.save(requestOnField);

        RequestOnField resultRequest = requestOnFieldRepository.findById(requestOnField.getId()).orElse(null);

        assertNotNull("Entity is null", resultRequest);
    }
}