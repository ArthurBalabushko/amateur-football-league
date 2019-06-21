package com.itacademy.database.util;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Location;
import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Sponsor;
import com.itacademy.database.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@Component
public final class TestDataHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public TestDataHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void deleteTestData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from Schedule ").executeUpdate();
        entityManager.createQuery("delete from RequestOnField ").executeUpdate();
        entityManager.createQuery("delete from FootballField ").executeUpdate();
        entityManager.createQuery("delete from Landlord ").executeUpdate();
        entityManager.createQuery("delete from Sponsor ").executeUpdate();
        entityManager.createQuery("delete from Invitation ").executeUpdate();
        entityManager.createQuery("delete from RequestInTeam ").executeUpdate();
        entityManager.createQuery("delete from Player ").executeUpdate();
        entityManager.createQuery("delete from Team ").executeUpdate();
        entityManager.createQuery("delete from Manager ").executeUpdate();
        entityManager.createQuery("delete from Position ").executeUpdate();
        entityManager.createQuery("delete from Role ").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void importTestData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Role rolePlayer = Role.builder()
                .name("player")
                .build();
        Role roleManager = Role.builder()
                .name("manager")
                .build();
        Role roleLandlord = Role.builder()
                .name("landlord")
                .build();
        entityManager.persist(rolePlayer);
        entityManager.persist(roleManager);
        entityManager.persist(roleLandlord);

        Manager manager1 = new Manager("Петя", "Петров",
                LocalDate.of(1992, 7, 10), "+375 33 378 12 34",
                "pp@mail.ru", "1234", roleManager);
        Manager manager2 = new Manager("Федор", "Федоров",
                LocalDate.of(1991, 10, 17), "+375 33 378 12 35",
                "ff@mail.ru", "1234", roleManager);
        Landlord landlord1 = new Landlord("Константин", "Костин",
                LocalDate.of(1988, 10, 17), "+375 33 378 33 35",
                "kk@mail.ru", "1234", roleLandlord);
        entityManager.persist(manager1);
        entityManager.persist(manager2);
        entityManager.persist(landlord1);

        Sponsor etihadAirways = Sponsor.builder()
                .name("Etihad Airways")
                .build();
        Sponsor emirates = Sponsor.builder()
                .name("Emirates")
                .build();
        Sponsor jeep = Sponsor.builder()
                .name("Jeep")
                .build();
        Sponsor xBet = Sponsor.builder()
                .name("1xBET")
                .build();
        entityManager.persist(etihadAirways);
        entityManager.persist(emirates);
        entityManager.persist(jeep);

        Location location = Location.builder()
                .city("Минск")
                .street("Серова")
                .build();

        FootballField footballField = FootballField.builder()
                .name("СК Динамо")
                .location(location)
                .phoneNumber("+375 17 327 36 11")
                .landlord(landlord1)
                .build();
        entityManager.persist(footballField);

        Team fcArsenal = Team.builder()
                .name("FC Arsenal")
                .manager(manager1)
                .build();
        Team fcBate = Team.builder()
                .name("FC Bate")
                .manager(manager2)
                .build();
        entityManager.persist(fcArsenal);
        entityManager.persist(fcBate);

        Position goalkeeper = Position.builder()
                .name("goalkeeper")
                .build();
        Position defender = Position.builder()
                .name("defender")
                .build();
        Position midfielder = Position.builder()
                .name("midfielder")
                .build();
        entityManager.persist(goalkeeper);
        entityManager.persist(defender);
        entityManager.persist(midfielder);

        Player player1 = new Player("Сергей", "Сергеев",
                LocalDate.of(1989, 12, 31), "+375 33 378 14 56",
                "ss@mail.ru", "1234", rolePlayer, goalkeeper, 180, 87, fcArsenal);
        Player player2 = new Player("Антон", "Антонов",
                LocalDate.of(1978, 12, 20), "+375 33 378 88 56",
                "aa@mail.ru", "1234", rolePlayer, defender, 190, 90, fcArsenal);
        Player player3 = new Player("Андрей", "Андреев",
                LocalDate.of(1989, 5, 31), "+375 33 448 14 56",
                "andr@mail.ru", "1234", rolePlayer, goalkeeper, 180, 87, fcArsenal);
        Player player4 = new Player("Павел", "Павлов",
                LocalDate.of(1988, 9, 24), "+375 33 498 10 66",
                "pavel@mail.ru", "1234", rolePlayer, defender, 189, 87, null);
        Player player5 = new Player("Ян", "Янковский",
                LocalDate.of(1993, 12, 24), "+375 33 498 19 66",
                "yan@mail.ru", "1234", rolePlayer, midfielder, 199, 97, fcBate);
        entityManager.persist(player1);
        entityManager.persist(player2);
        entityManager.persist(player3);
        entityManager.persist(player4);
        entityManager.persist(player5);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
