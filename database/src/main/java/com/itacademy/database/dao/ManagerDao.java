package com.itacademy.database.dao;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QManager.manager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagerDao implements BaseDao<Long, Manager> {

    private static final ManagerDao INSTANCE = new ManagerDao();

    public Optional<Manager> findByEmail(String email) {
        Manager resultManager = new JPAQuery<Manager>(SessionManager.getSession())
                .select(manager)
                .from(manager)
                .where(manager.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(resultManager);
    }

    public static ManagerDao getInstance() {
        return INSTANCE;
    }
}
