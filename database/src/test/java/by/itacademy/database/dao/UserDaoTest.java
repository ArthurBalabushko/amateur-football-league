package by.itacademy.database.dao;

import by.itacademy.database.entity.Role;
import by.itacademy.database.entity.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class UserDaoTest {

    @Test
    public void findById() {

        User user = User.builder()
                .id(1L)
                .firstName("Артур")
                .lastName("Балабушко")
                .birthDay(LocalDate.of(1990, 05, 07))
                .phoneNumber("+375 33 378 38 48")
                .email("an.balabushko@gmail.com")
                .password("1234")
                .role(Role.builder()
                        .id(1)
                        .name("admin")
                        .build())
                .build();

        Integer userId = 1;

        Optional<User> resultUser = UserDao.getInstance().findById(userId);
        resultUser.ifPresent(value -> assertEquals(user, value));
    }
}
