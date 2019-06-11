package com.itacademy.database.dao;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Team;
import com.itacademy.database.util.SessionManager;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.itacademy.database.entity.QPlayer.player;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerDao implements BaseDao<Long, Player> {

    private static final PlayerDao INSTANCE = new PlayerDao();

    public Optional<Player> findByEmail(String email) {
        @Cleanup Player resultPlayer = new JPAQuery<Player>(SessionManager.getSession())
                .select(player)
                .from(player)
                .where(player.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(resultPlayer);
    }

    public List<Player> findAllByPosition(Position position) {
        @Cleanup List<Player> players = new JPAQuery<Player>(SessionManager.getSession())
                .select(player)
                .from(player)
                .where(player.position.eq(position))
                .orderBy(player.lastName.asc())
                .fetch();

        return players;
    }

    public List<Player> findAllByTeam(Team team) {
        @Cleanup List<Player> players = new JPAQuery<Player>(SessionManager.getSession())
                .select(player)
                .from(player)
                .where(player.team.eq(team))
                .orderBy(player.lastName.asc())
                .fetch();

        return players;
    }

    public List<Player> findAllByFilter(FilterPlayer filter) {

        BooleanExpression criterion;
        String teamValue = "noTeam";

        if (teamValue.equals(filter.getTeam())) {
            criterion = player.team.isNull().and(player.position.name.eq(filter.getPosition())
                    .and(player.birthDay.between(
                            LocalDate.of(LocalDate.now().getYear() - filter.getAgeTo(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                            LocalDate.of(LocalDate.now().getYear() - filter.getAgeFrom(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))));
        } else {
            criterion = player.position.name.eq(filter.getPosition())
                    .and(player.birthDay.between(
                            LocalDate.of(LocalDate.now().getYear() - filter.getAgeTo(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                            LocalDate.of(LocalDate.now().getYear() - filter.getAgeFrom(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())));
        }

        @Cleanup List<Player> players = new JPAQuery<Player>(SessionManager.getSession())
                .select(player)
                .from(player)
                .where(criterion)
                .orderBy(player.lastName.asc())
                .offset(filter.getOffset())
                .limit(filter.getLimit())
                .fetch();

        return players;
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }
}
