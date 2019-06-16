package com.itacademy.database.repository;

import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.RequestInTeam;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class RequestInTeamDaoTest extends BaseCase {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RequestInTeamRepository requestInTeamRepository;

    @Test
    public void save() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        Player player = playerRepository.findByEmail("ss@mail.ru").orElse(null);

        RequestInTeam request = RequestInTeam.builder()
                .date(LocalDate.now())
                .player(player)
                .team(team)
                .status(Status.WAITING)
                .build();

        requestInTeamRepository.save(request);

        assertNotNull(request.getId());
    }

    @Test
    public void findById() {
        Team team = teamRepository.findByName("FC Bate").orElse(null);
        Player player = playerRepository.findByEmail("ss@mail.ru").orElse(null);

        RequestInTeam request = RequestInTeam.builder()
                .date(LocalDate.now())
                .player(player)
                .team(team)
                .status(Status.WAITING)
                .build();

        requestInTeamRepository.save(request);

        RequestInTeam resultRequest = requestInTeamRepository.findById(request.getId()).orElse(null);

        assertNotNull("Entity is null", resultRequest);
    }
}