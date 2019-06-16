package com.itacademy.database.repository;

import com.itacademy.database.entity.Landlord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandlordRepository extends CrudRepository<Landlord, Long> {

    Optional<Landlord> findByEmail(String email);
}
