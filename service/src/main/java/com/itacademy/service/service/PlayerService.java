package com.itacademy.service.service;

import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.mapper.PlayerMapper;
import com.itacademy.database.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<ViewPlayerDto> findByFilter(FilterPlayer filterPlayer) {

        return playerRepository.findByFilter(filterPlayer).stream()
                .map(it -> PlayerMapper.getInstance().mapToDto(it)).collect(Collectors.toList());
    }
}
