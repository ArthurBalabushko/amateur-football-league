package com.itacademy.database.dao;

import com.itacademy.database.entity.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    @SneakyThrows
    public Player save(Session session, Player player) {
        session.save(player);

        return player;
    }

    @SneakyThrows
    public Optional<Player> findById(Session session, Long id) {
        Player player = session.get(Player.class, id);

        return ofNullable(player);
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }
}
