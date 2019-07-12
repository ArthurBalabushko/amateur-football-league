package com.itacademy.web.controller;

import com.itacademy.database.entity.User;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyOfficeController {

    private final UserService userService;
    private final PlayerService playerService;

    @GetMapping("/my-office")
    public String showMyOffice(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        switch (user.getRole().getName()){
            case "PLAYER":
                model.addAttribute("player", playerService.findById(user.getId()));
                break;
        }

        return "my-office";
    }
}
