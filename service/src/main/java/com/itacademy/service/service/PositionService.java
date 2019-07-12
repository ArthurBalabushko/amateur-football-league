package com.itacademy.service.service;

import com.itacademy.database.dto.ViewPositionDto;
import com.itacademy.database.entity.Position;
import com.itacademy.database.repository.PositionRepository;
import com.itacademy.service.mapper.PositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    public List<ViewPositionDto> findAll() {
        List<Position> positions = new ArrayList<>();
        positionRepository.findAll().iterator().forEachRemaining(positions::add);

        return positions.stream().map(it -> ViewPositionDto.builder()
                .id(it.getId())
                .name(it.getName())
                .build()).collect(Collectors.toList());
    }

    public ViewPositionDto findById(Integer positionId) {
        ViewPositionDto positionDto = null;

        Optional<Position> position = positionRepository.findById(positionId);
        if (position.isPresent()) {
            positionDto = positionMapper.positionToPositionDto(position.get());
        }

        return positionDto;
    }
}
