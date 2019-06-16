package com.itacademy.database.repository;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.entity.Player;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.itacademy.database.entity.QPlayer.player;

@Repository
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Player> findByFilter(FilterPlayer filterPlayer) {
        BooleanExpression criterion;
        String teamValue = "noTeam";

        if (teamValue.equals(filterPlayer.getTeam())) {
            criterion = player.team.isNull().and(player.position.name.eq(filterPlayer.getPosition())
                    .and(player.birthDay.between(
                            LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeTo(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                            LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeFrom(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))));
        } else {
            criterion = player.position.name.eq(filterPlayer.getPosition())
                    .and(player.birthDay.between(
                            LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeTo(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                            LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeFrom(),
                                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())));
        }

        return new JPAQuery<Player>(entityManager)
                .select(player)
                .from(player)
                .where(criterion)
                .orderBy(player.lastName.asc())
                .offset(filterPlayer.getOffset())
                .limit(filterPlayer.getLimit())
                .fetch();
    }
}
