package com.itacademy.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewPlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String phoneNumber;
    private String email;
    private String role;
    private String position;
    private Integer growth;
    private Integer weight;
    private String team;
}
