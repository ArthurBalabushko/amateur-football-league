package com.itacademy.database.repository;

import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.database.entity.Player;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.itacademy.database.entity.QPlayer.player;

public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Player> findByFilter(FilterPlayer filterPlayer) {
        BooleanExpression criterion;
        String teamValue = "noTeam";

        int offset = 0;
        if (filterPlayer.getPage() > 1) {
            offset = (filterPlayer.getPage() * filterPlayer.getLimit()) - filterPlayer.getLimit();
        }

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
                .offset(offset)
                .limit(filterPlayer.getLimit())
                .fetch();
    }
}
