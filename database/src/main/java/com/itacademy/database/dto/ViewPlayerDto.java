package com.itacademy.database.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ViewPlayerDto extends BaseUserDto {

    private ViewPositionDto position;
    private Integer growth;
    private Integer weight;
    private ViewTeamDto team;

    @Builder
    public ViewPlayerDto(Long id, String firstName, String lastName, String birthDay, String phoneNumber, String email,
                         ViewRoleDto role, ViewPositionDto position, Integer growth, Integer weight, ViewTeamDto team) {

        super(id, firstName, lastName, birthDay, phoneNumber, email, role);
        this.position = position;
        this.growth = growth;
        this.weight = weight;
        this.team = team;
    }
}
