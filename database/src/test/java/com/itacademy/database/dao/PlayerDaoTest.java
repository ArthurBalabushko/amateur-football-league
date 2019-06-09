package com.itacademy.database.dao;

import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Team;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PlayerDaoTest extends BaseTest {

    private PlayerDao playerDao = PlayerDao.getInstance();

    @Test
    public void save() {
        Role role = RoleDao.getInstance().findByName("manager").orElse(null);
        Position position = PositionDao.getInstance().findByName("defender").orElse(null);

        Player player = new Player("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role, position, 190, 90, null);

        playerDao.save(player);

        assertNotNull(player.getId());
    }

    @Test
    public void findById() {
        Role role = RoleDao.getInstance().findByName("manager").orElse(null);
        Position position = PositionDao.getInstance().findByName("defender").orElse(null);

        Player player = new Player("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role, position, 190, 90, null);

        playerDao.save(player);

        Player resultPlayer = playerDao.findById(player.getId()).orElse(null);

        assertNotNull("Entity is null", resultPlayer);
    }

    @Test
    public void findByEmail() {
        Player resultPlayer = playerDao.findByEmail("ss@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultPlayer);
    }

    @Test
    public void findAllByPosition() {
        Position position = PositionDao.getInstance().findByName("goalkeeper").orElse(null);
        List<Player> players = playerDao.findAllByPosition(position);

        assertThat(players, hasSize(2));
    }

    @Test
    public void findAllByTeam() {
        Team team = TeamDao.getInstance().findByName("FC Arsenal").orElse(null);
        List<Player> players = playerDao.findAllByTeam(team);

        assertThat(players, hasSize(3));
    }

    @Test
    public void delete() {
        Player player = playerDao.findByEmail("yan@mail.ru").orElse(null);

        playerDao.delete(player);

        Player resultPlayer = playerDao.findByEmail("yan@mail.ru").orElse(null);

        assertNull(resultPlayer);
    }

    @Test
    public void update() {
        Team team = TeamDao.getInstance().findByName("FC Bate").orElse(null);
        Player player = playerDao.findByEmail("pavel@mail.ru").orElse(null);
        player.setTeam(team);

        playerDao.update(player);

        Player resultPlayer = playerDao.findByEmail("pavel@mail.ru").orElse(null);

        assertEquals(resultPlayer.getTeam(), team);
    }

    @Test
    public void findAll() {
        List<Player> players = playerDao.findAll();
        List<String> emails = players.stream().map(Player::getEmail).collect(Collectors.toList());

        assertThat(emails, contains("ss@mail.ru", "aa@mail.ru", "andr@mail.ru", "pavel@mail.ru"));
    }
}
