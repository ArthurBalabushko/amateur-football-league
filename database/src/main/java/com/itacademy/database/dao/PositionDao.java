package com.itacademy.database.dao;

import com.itacademy.database.entity.Position;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionDao {

    private static final PositionDao INSTANCE = new PositionDao();

    @SneakyThrows
    public Position save(Session session, Position position) {
        session.save(position);

        return position;
    }

    @SneakyThrows
    public Optional<Position> findById(Session session, Integer id) {
        Position position = session.get(Position.class, id);

        return ofNullable(position);
    }

    public static PositionDao getInstance() {
        return INSTANCE;
    }
}
