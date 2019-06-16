package com.itacademy.database.repository;

import com.itacademy.database.entity.FootballField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FootballFieldRepository extends CrudRepository<FootballField, Long> {

    Optional<FootballField> findByName(String name);
}
