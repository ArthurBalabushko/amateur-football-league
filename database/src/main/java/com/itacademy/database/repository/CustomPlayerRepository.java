package com.itacademy.database.repository;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPlayerRepository {

    List<Player> findByFilter(FilterPlayer filterPlayer);
}
