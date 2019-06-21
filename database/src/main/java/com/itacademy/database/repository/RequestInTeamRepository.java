package com.itacademy.database.repository;

import com.itacademy.database.entity.RequestInTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestInTeamRepository extends CrudRepository<RequestInTeam, Long> {
}
