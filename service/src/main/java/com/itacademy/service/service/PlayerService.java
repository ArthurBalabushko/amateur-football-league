package com.itacademy.service.service;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.dao.PlayerDao;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.mapper.PlayerMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {

    private static final PlayerService INSTANCE = new PlayerService();

    public Optional<ViewPlayerDto> findById(Long id) {

        return PlayerDao.getInstance().findById(id).map(it -> ViewPlayerDto.builder()
                .id(it.getId())
                .firstName(it.getFirstName())
                .lastName(it.getLastName())
                .birthDay(it.getBirthDay().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                .phoneNumber(it.getPhoneNumber())
                .email(it.getEmail())
                .role(it.getRole().getName())
                .build());
    }

    public List<ViewPlayerDto> findAllByFilter(FilterPlayer filterPlayer) {

        return PlayerDao.getInstance().findAllByFilter(filterPlayer).stream()
                .map(it -> PlayerMapper.getInstance().mapToDto(it)).collect(Collectors.toList());
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
