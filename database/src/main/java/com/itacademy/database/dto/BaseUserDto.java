package com.itacademy.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String phoneNumber;
    private String email;
    private ViewRoleDto role;
}
