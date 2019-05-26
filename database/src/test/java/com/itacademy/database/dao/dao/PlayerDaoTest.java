package com.itacademy.database.dao.dao;

import com.itacademy.database.dao.PlayerDao;
import com.itacademy.database.dao.PositionDao;
import com.itacademy.database.dao.RoleDao;
import com.itacademy.database.dao.TeamDao;
import com.itacademy.database.dao.util.TestDataHelper;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Team;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PlayerDaoTest {

    private PlayerDao playerDao = PlayerDao.getInstance();
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        TestDataHelper.getInstance().importTestData(sessionFactory);
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

    @Test
    public void save() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role role = RoleDao.getInstance().findById(session, 1).orElse(null);

        Position position = PositionDao.getInstance().findById(session, 1).orElse(null);

        Player player = new Player("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role, position, 190, 90, null);

        playerDao.save(session, player);

        session.getTransaction().commit();

        assertNotNull(player.getId());
    }

    @Test
    public void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long playerId = 1L;
        Optional<Player> resultPlayer = playerDao.findById(session, playerId);
        Integer roleId = 2;
        Optional<Role> role = RoleDao.getInstance().findById(session, roleId);
        Integer positionId = 1;
        Optional<Position> position = PositionDao.getInstance().findById(session, positionId);
        Long teamId = 1L;
        Optional<Team> team = TeamDao.getInstance().findById(session, teamId);

        session.getTransaction().commit();

        if (resultPlayer.isPresent() && role.isPresent()) {

            Player expectedPlayer = new Player("Сергей", "Сергеев",
                    LocalDate.of(1989, 12, 31), "+375 33 378 14 56",
                    "ss@mail.ru", "1234", role.get(), position.get(), 180, 87, team.get());

            assertEquals(expectedPlayer.getEmail(), resultPlayer.get().getEmail());
            assertThat(resultPlayer.get().getTeam().getName(), equalTo("FC Arsenal"));
        }
    }
}
