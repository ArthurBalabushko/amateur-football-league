package com.itacademy.database.dao;

import com.itacademy.database.entity.Team;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamDao {

    private static final TeamDao INSTANCE = new TeamDao();

    @SneakyThrows
    public Team save(Session session, Team team) {
        session.save(team);

        return team;
    }

    @SneakyThrows
    public Optional<Team> findById(Session session, Long id) {
        Team team = session.get(Team.class, id);

        return ofNullable(team);
    }

    public static TeamDao getInstance() {
        return INSTANCE;
    }
}
