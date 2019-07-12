package com.itacademy.database.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ViewManagerDto extends BaseUserDto {

    private ViewTeamDto teamManager;

    @Builder
    public ViewManagerDto(Long id, String firstName, String lastName, String birthDay, String phoneNumber,
                          String email, ViewRoleDto role, ViewTeamDto teamManager) {

        super(id, firstName, lastName, birthDay, phoneNumber, email, role);
        this.teamManager = teamManager;
    }
}

