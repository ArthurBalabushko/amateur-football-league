package com.itacademy.database.mapper;

import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.entity.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerMapper implements BaseMapper<Player, ViewPlayerDto> {

    private static PlayerMapper ourInstance = new PlayerMapper();

    @Override
    public ViewPlayerDto mapToDto(Player entity) {
        ViewPlayerDto result;
        if (entity.getTeam() != null) {
            result = ViewPlayerDto.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .birthDay(entity.getBirthDay().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                    .phoneNumber(entity.getPhoneNumber())
                    .email(entity.getEmail())
                    .role(entity.getRole().getName())
                    .position(entity.getPosition().getName())
                    .growth(entity.getGrowth())
                    .weight(entity.getWeight())
                    .team(entity.getTeam().getName())
                    .build();
        } else {
            result = ViewPlayerDto.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .birthDay(entity.getBirthDay().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                    .phoneNumber(entity.getPhoneNumber())
                    .email(entity.getEmail())
                    .role(entity.getRole().getName())
                    .position(entity.getPosition().getName())
                    .growth(entity.getGrowth())
                    .weight(entity.getWeight())
                    .team(null)
                    .build();
        }

        return result;
    }

    public static PlayerMapper getInstance() {
        return ourInstance;
    }
}
