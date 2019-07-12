package com.itacademy.service.service;

import com.itacademy.database.dto.CreatePlayerDto;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.database.repository.PlayerRepository;
import com.itacademy.database.repository.PositionRepository;
import com.itacademy.service.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PositionRepository positionRepository;
    private final PlayerMapper playerMapper;
    private final PasswordEncoder passwordEncoder;

    public ViewPlayerDto findById(Long playerId) {
        ViewPlayerDto playerDto = null;

        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isPresent()) {
            playerDto = playerMapper.playerToPlayerDto(player.get());
        }

        return playerDto;
    }

    public ViewPlayerDto findByEmail(String email) {
        ViewPlayerDto playerDto = null;

        Optional<Player> player = playerRepository.findByEmail(email);
        if (player.isPresent()) {
            playerDto = playerMapper.playerToPlayerDto(player.get());
        }

        return playerDto;
    }

    public Page<ViewPlayerDto> findByFilter(FilterPlayer filterPlayer, Pageable pageable) {

        Page<ViewPlayerDto> page = null;
        Position position = positionRepository.findByName(filterPlayer.getPosition()).orElse(null);
        LocalDate ageTo = LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeFrom(),
                LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        LocalDate ageFrom = LocalDate.of(LocalDate.now().getYear() - filterPlayer.getAgeTo(),
                LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());

        if ("noTeam".equals(filterPlayer.getTeam())) {
            page = playerRepository.findAllByPositionAndTeamNullAndBirthDayBetween(position, ageFrom, ageTo, pageable)
                    .map(playerMapper::playerToPlayerDto);
        } else if ("all".equals(filterPlayer.getTeam())) {
            page = playerRepository.findAllByPositionAndAndBirthDayBetween(position, ageFrom, ageTo, pageable)
                    .map(playerMapper::playerToPlayerDto);
        }

        return page;
    }

    public ViewPlayerDto save(CreatePlayerDto playerDto) {

        playerDto.setPassword(passwordEncoder.encode(playerDto.getPassword()));

        Player player = playerRepository.save(playerMapper.createPlayerDtoToPlayer(playerDto));

        return playerMapper.playerToPlayerDto(player);
    }
}
