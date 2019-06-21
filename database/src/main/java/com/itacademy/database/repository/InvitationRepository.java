package com.itacademy.database.repository;

import com.itacademy.database.entity.Invitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long> {
}
