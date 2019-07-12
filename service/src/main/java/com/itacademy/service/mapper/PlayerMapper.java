package com.itacademy.service.mapper;

import com.itacademy.database.dto.CreatePlayerDto;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.entity.Player;
import com.itacademy.service.formatter.DateFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Mapper(componentModel = "spring", uses = {PositionMapper.class, RoleMapper.class})
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "birthDay", source = "player.birthDay", dateFormat = "dd MMMM yyyy")})
    ViewPlayerDto playerToPlayerDto(Player player);

    Player playerDtoToPlayer(ViewPlayerDto playerDto);

    @Mappings({
            @Mapping(target = "birthDay", source = "player.birthDay", dateFormat = "dd MMMM yyyy")})
    CreatePlayerDto playerToCreatePlayerDto(Player player);

    @Mappings({
            @Mapping(target = "birthDay", qualifiedBy = DateTimeFormat.class)})
    Player createPlayerDtoToPlayer(CreatePlayerDto createPlayerDto);

    @DateTimeFormat
    default LocalDate format(String date) {
        return new DateFormat().format(date);
    }
}
