package com.itacademy.service.service;

import com.itacademy.database.dao.PositionDao;
import com.itacademy.database.dto.ViewPositionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionService {

    private static final PositionService INSTANCE = new PositionService();

    public List<ViewPositionDto> findAll() {

        return PositionDao.getInstance().findAll().stream().map(it -> ViewPositionDto.builder()
                .name(it.getName())
                .build()).collect(Collectors.toList());
    }

    public static PositionService getInstance() {
        return INSTANCE;
    }
}
