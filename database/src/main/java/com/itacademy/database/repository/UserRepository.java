package com.itacademy.database.repository;

import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User u set u.role = :role where id = :id")
    int update(@Param("role") Role role, @Param("id") Long id);
}
