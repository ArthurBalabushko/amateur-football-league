package com.itacademy.database.dao;

import com.itacademy.database.entity.Position;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QPosition.position;
import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDao implements BaseDao<Integer, Position> {

    private static final PositionDao INSTANCE = new PositionDao();

    public Optional<Position> findByName(String name) {
        Position resultPosition = new JPAQuery<Position>(SessionManager.getSession())
                .select(position)
                .from(position)
                .where(position.name.eq(name))
                .fetchOne();

        return ofNullable(resultPosition);
    }

    public static PositionDao getInstance() {
        return INSTANCE;
    }
}
