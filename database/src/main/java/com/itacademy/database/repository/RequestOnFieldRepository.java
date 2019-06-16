package com.itacademy.database.repository;

import com.itacademy.database.entity.RequestOnField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOnFieldRepository extends CrudRepository<RequestOnField, Long> {
}
