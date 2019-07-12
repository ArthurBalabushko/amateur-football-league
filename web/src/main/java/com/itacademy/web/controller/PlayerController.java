package com.itacademy.web.controller;

import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerController {

    private final PlayerService playerService;
    private final PositionService positionService;

    @ModelAttribute
    public void setReferences(Model model) {
        model.addAttribute("positions", positionService.findAll());
    }

    @GetMapping("/players")
    public String getPlayers() {
        return "players";
    }

    @PostMapping("/players")
    public String findPlayers(Model model, FilterPlayer filterPlayer) {
        List<ViewPlayerDto> listPlayers = playerService.findByFilter(filterPlayer);
        model.addAttribute("listPlayers", listPlayers);

        return "players";
    }
}
