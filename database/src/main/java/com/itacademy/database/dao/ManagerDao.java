package com.itacademy.database.dao;

import com.itacademy.database.entity.Manager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagerDao {

    private static final ManagerDao INSTANCE = new ManagerDao();

    @SneakyThrows
    public Manager save(Session session, Manager manager) {
        session.save(manager);

        return manager;
    }

    @SneakyThrows
    public Optional<Manager> findById(Session session, Long id) {
        Manager manager = session.get(Manager.class, id);

        return ofNullable(manager);
    }

    public static ManagerDao getInstance() {
        return INSTANCE;
    }
}
