package com.itacademy.database.dao;

import com.itacademy.database.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();

    @SneakyThrows
    public Role save(Session session, Role role) {
        session.save(role);

        return role;
    }

    @SneakyThrows
    public Optional<Role> findById(Session session, Integer id) {
        Role role = session.get(Role.class, id);

        return ofNullable(role);
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
