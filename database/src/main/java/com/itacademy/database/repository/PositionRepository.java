package com.itacademy.database.repository;

import com.itacademy.database.entity.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {

    Optional<Position> findByName(String name);
}
