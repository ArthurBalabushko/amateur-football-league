package com.itacademy.database.repository;

import com.itacademy.database.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Schedule.ScheduleId> {
}
