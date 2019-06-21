package com.itacademy.database.repository;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.entity.Player;

import java.util.List;

public interface CustomPlayerRepository {

    List<Player> findByFilter(FilterPlayer filterPlayer);
}
