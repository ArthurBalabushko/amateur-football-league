package com.itacademy.database.dao;

import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.RequestInTeam;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class RequestInTeamDaoTest extends BaseTest {

    private RequestInTeamDao requestInTeamDao = RequestInTeamDao.getInstance();

    @Test
    public void save() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        Player player = PlayerDao.getInstance().findByEmail("ss@mail.ru").orElse(null);

        RequestInTeam request = RequestInTeam.builder()
                .date(LocalDate.now())
                .player(player)
                .team(team)
                .status(Status.WAITING)
                .build();

        requestInTeamDao.save(request);

        assertNotNull(request);
    }

    @Test
    public void findById() {
        Team team = TeamDao.getInstance().findByName("FC Bate").orElse(null);
        Player player = PlayerDao.getInstance().findByEmail("ss@mail.ru").orElse(null);

        RequestInTeam request = RequestInTeam.builder()
                .date(LocalDate.now())
                .player(player)
                .team(team)
                .status(Status.WAITING)
                .build();

        requestInTeamDao.save(request);

        RequestInTeam resultRequest = requestInTeamDao.findById(request.getId()).orElse(null);

        assertNotNull("Entity is null", resultRequest);
    }
}