package com.itacademy.database.dao;

import com.itacademy.database.entity.Invitation;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Status;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class InvitationDaoTest extends BaseTest {

    private InvitationDao invitationDao = InvitationDao.getInstance();

    @Test
    public void save() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        Player player = PlayerDao.getInstance().findByEmail("ss@mail.ru").orElse(null);

        Invitation invitation = Invitation.builder()
                .date(LocalDate.now())
                .team(team)
                .player(player)
                .status(Status.WAITING)
                .build();

        invitationDao.save(invitation);

        assertNotNull(invitation);
    }

    @Test
    public void findById() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        Player player = PlayerDao.getInstance().findByEmail("ss@mail.ru").orElse(null);

        Invitation invitation = Invitation.builder()
                .date(LocalDate.now())
                .team(team)
                .player(player)
                .status(Status.WAITING)
                .build();

        invitationDao.save(invitation);

        Invitation resultInvitation = invitationDao.findById(invitation.getId()).orElse(null);

        assertNotNull("Entity is null", resultInvitation);
    }
}