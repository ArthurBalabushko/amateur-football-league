package com.itacademy.database.repository;

import com.itacademy.database.entity.Invitation;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class InvitationDaoTest extends BaseCase {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Test
    public void save() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        Player player = playerRepository.findByEmail("ss@mail.ru").orElse(null);

        Invitation invitation = Invitation.builder()
                .date(LocalDate.now())
                .team(team)
                .player(player)
                .status(Status.WAITING)
                .build();

        invitationRepository.save(invitation);

        assertNotNull(invitation);
    }

    @Test
    public void findById() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        Player player = playerRepository.findByEmail("ss@mail.ru").orElse(null);

        Invitation invitation = Invitation.builder()
                .date(LocalDate.now())
                .team(team)
                .player(player)
                .status(Status.WAITING)
                .build();

        invitationRepository.save(invitation);

        Invitation resultInvitation = invitationRepository.findById(invitation.getId()).orElse(null);

        assertNotNull("Entity is null", resultInvitation);
    }
}