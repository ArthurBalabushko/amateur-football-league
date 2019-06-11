package com.itacademy.database.dao;

import com.itacademy.database.entity.Schedule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleDao implements BaseDao<Schedule.ScheduleId, Schedule> {

    private static final ScheduleDao INSTANCE = new ScheduleDao();

    public static ScheduleDao getInstance() {
        return INSTANCE;
    }
}
