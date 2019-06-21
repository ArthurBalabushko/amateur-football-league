package com.itacademy.database.repository;

import com.itacademy.database.entity.Sponsor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
}
