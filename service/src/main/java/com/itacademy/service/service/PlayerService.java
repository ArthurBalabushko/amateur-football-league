package com.itacademy.service.service;

import com.itacademy.database.connection.SessionFactoryUtil;
import com.itacademy.database.dao.PlayerDao;
import com.itacademy.database.dto.ViewPlayerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {

    private static final PlayerService INSTANCE = new PlayerService();

    public Optional<ViewPlayerDto> findById(Long id) {

        return PlayerDao.getInstance().findById(SessionFactoryUtil.getSessionFactory().openSession(), id).map(it -> ViewPlayerDto.builder()
                .id(it.getId())
                .firstName(it.getFirstName())
                .lastName(it.getLastName())
                .birthDay(it.getBirthDay().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                .phoneNumber(it.getPhoneNumber())
                .email(it.getEmail())
                .role(it.getRole().getName())
                .build());
    }

    public void save(ViewPlayerDto viewPlayerDto) {

    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
