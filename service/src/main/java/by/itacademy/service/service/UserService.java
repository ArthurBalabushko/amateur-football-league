package by.itacademy.service.service;

import by.itacademy.database.dao.UserDao;
import by.itacademy.database.dto.ViewUserInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public Optional<ViewUserInfoDto> findById(Integer id) {

        return UserDao.getInstance().findById(id).map(it -> ViewUserInfoDto.builder()
                .id(it.getId())
                .firstName(it.getFirstName())
                .lastName(it.getLastName())
                .birthDay(it.getBirthDay().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                .phoneNumber(it.getPhoneNumber())
                .email(it.getEmail())
                .role(it.getRole().getName())
                .build());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
