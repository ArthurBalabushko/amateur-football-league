package com.itacademy.database.dao;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Schedule;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;

public class ScheduleDaoTest extends BaseTest {

    private ScheduleDao scheduleDao = ScheduleDao.getInstance();

    @Test
    public void save() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        FootballField footballfield = FootballFieldDao.getInstance().findByName("СК Динамо").orElse(null);

        Schedule schedule = Schedule.builder()
                .id(Schedule.ScheduleId.builder()
                        .footballFieldId(footballfield.getId())
                        .date(LocalDate.of(2019, 9, 23))
                        .timeStart(LocalTime.of(19, 00))
                        .timeFinish(LocalTime.of(21, 00))
                        .build())
                .team(team)
                .build();

        scheduleDao.save(schedule);

        assertNotNull(schedule.getId());
    }

    @Test
    public void findById() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        FootballField footballfield = FootballFieldDao.getInstance().findByName("СК Динамо").orElse(null);

        Schedule schedule = Schedule.builder()
                .id(Schedule.ScheduleId.builder()
                        .footballFieldId(footballfield.getId())
                        .date(LocalDate.of(2019, 9, 24))
                        .timeStart(LocalTime.of(19, 00))
                        .timeFinish(LocalTime.of(21, 00))
                        .build())
                .team(team)
                .build();

        scheduleDao.save(schedule);

        Schedule resultSchedule = scheduleDao.findById(schedule.getId()).orElse(null);

        assertNotNull("Entity is null", resultSchedule);
    }

}