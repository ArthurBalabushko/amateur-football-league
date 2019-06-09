package com.itacademy.database.dao;

import com.itacademy.database.entity.Role;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QRole.role;
import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDao implements BaseDao<Integer, Role> {

    private static final RoleDao INSTANCE = new RoleDao();

    public Optional<Role> findByName(String name) {
        Role resultRole = new JPAQuery<Role>(SessionManager.getSession())
                .select(role)
                .from(role)
                .where(role.name.eq(name))
                .fetchOne();

        return ofNullable(resultRole);
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
