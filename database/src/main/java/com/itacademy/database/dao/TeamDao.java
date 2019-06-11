package com.itacademy.database.dao;

import com.itacademy.database.entity.Team;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QTeam.team;
import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamDao implements BaseDao<Long, Team> {

    private static final TeamDao INSTANCE = new TeamDao();

    public Optional<Team> findByName(String name) {
        @Cleanup Team resultTeam = new JPAQuery<Team>(SessionManager.getSession())
                .select(team)
                .from(team)
                .where(team.name.eq(name))
                .fetchOne();

        return ofNullable(resultTeam);
    }

    public static TeamDao getInstance() {
        return INSTANCE;
    }
}
