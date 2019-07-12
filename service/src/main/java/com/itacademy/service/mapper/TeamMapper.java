package com.itacademy.service.mapper;

import com.itacademy.database.dto.CreateTeamDto;
import com.itacademy.database.dto.ViewTeamDto;
import com.itacademy.database.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeamMapper {

    ViewTeamDto teamToTeamDto(Team team);

    Team teamDtoToTeam(ViewTeamDto teamDto);

    Team createTeamDtoToTeam(CreateTeamDto teamDto);
}
