package com.itacademy.database.Filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterPlayer {
    private Integer ageFrom;
    private Integer ageTo;
    private String position;
    private String team;
    private Integer limit;
    private Integer offset;
}
