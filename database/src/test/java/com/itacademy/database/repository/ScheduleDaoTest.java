package com.itacademy.database.repository;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Schedule;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;

public class ScheduleDaoTest extends BaseCase {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FootballFieldRepository footballFieldRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void save() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        FootballField footballfield = footballFieldRepository.findByName("СК Динамо").orElse(null);

        Schedule schedule = Schedule.builder()
                .id(Schedule.ScheduleId.builder()
                        .footballField(footballfield)
                        .date(LocalDate.of(2019, 9, 23))
                        .timeStart(LocalTime.of(19, 00))
                        .timeFinish(LocalTime.of(21, 00))
                        .build())
                .team(team)
                .build();

        scheduleRepository.save(schedule);

        assertNotNull(schedule.getId());
    }

    @Test
    public void findById() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        FootballField footballfield = footballFieldRepository.findByName("СК Динамо").orElse(null);

        Schedule schedule = Schedule.builder()
                .id(Schedule.ScheduleId.builder()
                        .footballField(footballfield)
                        .date(LocalDate.of(2019, 9, 24))
                        .timeStart(LocalTime.of(19, 00))
                        .timeFinish(LocalTime.of(21, 00))
                        .build())
                .team(team)
                .build();

        scheduleRepository.save(schedule);

        Schedule resultSchedule = scheduleRepository.findById(schedule.getId()).orElse(null);

        assertNotNull("Entity is null", resultSchedule);
    }
}