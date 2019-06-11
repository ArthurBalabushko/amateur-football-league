package com.itacademy.database.dao;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class TeamDaoTest extends BaseTest {

    private TeamDao teamDao = TeamDao.getInstance();

    @Test
    public void save() {

        Manager manager = ManagerDao.getInstance().findByEmail("pp@mail.ru").orElse(null);

        Team team = Team.builder()
                .name("test")
                .manager(manager)
                .build();

        teamDao.save(team);

        assertNotNull(team.getId());
    }

    @Test
    public void findById() {
        Manager manager = ManagerDao.getInstance().findByEmail("ff@mail.ru").orElse(null);

        Team team = Team.builder()
                .name("test2")
                .manager(manager)
                .build();

        teamDao.save(team);

        Team resultTeam = teamDao.findById(team.getId()).orElse(null);

        assertNotNull("Entity is null", resultTeam);
    }

    @Test
    public void findByName() {
        Team team = teamDao.findByName("FC Arsenal").orElse(null);

        assertNotNull("Entity is null", team);
    }
}
