package com.itacademy.database.repository;

import com.itacademy.database.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);
}
