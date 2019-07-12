package com.itacademy.service.service;

import com.itacademy.database.dto.CreateTeamDto;
import com.itacademy.database.dto.ViewTeamDto;
import com.itacademy.database.entity.Team;
import com.itacademy.database.repository.TeamRepository;
import com.itacademy.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public ViewTeamDto findById(Long teamId) {
        ViewTeamDto teamDto = null;

        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            teamDto = teamMapper.teamToTeamDto(team.get());
        }

        return teamDto;
    }

    public ViewTeamDto save(CreateTeamDto teamDto) {

        Team team = teamRepository.save(teamMapper.createTeamDtoToTeam(teamDto));

        return teamMapper.teamToTeamDto(team);
    }
}
