package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Embeddable
public class Location {

    @Column(name = "location_city")
    private String city;

    @Column(name = "location_street")
    private String street;
}
