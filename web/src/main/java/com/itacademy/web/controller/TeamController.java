package com.itacademy.web.controller;

import com.itacademy.service.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/team/{id}")
    public String getTeam(Model model, @PathVariable("id") Long id) {
        model.addAttribute("team", teamService.findById(id));

        return "team";
    }
}
