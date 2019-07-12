package com.itacademy.web.controller;

import com.itacademy.database.dto.CreatePlayerDto;
import com.itacademy.database.dto.CreateUserDto;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.service.formatter.PhoneFormat;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.PositionService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CeatePlayerController {

    private final PlayerService playerService;
    private final UserService userService;
    private final PositionService positionService;
    private final RoleService roleService;
    private final PhoneFormat phoneFormat;

    @ModelAttribute
    public void setReferences(Model model) {
        model.addAttribute("positions", positionService.findAll());
    }

    @GetMapping("/create-player")
    public String createPlayerPage() {

        return "create-player";
    }

    @PostMapping("/create-player")
    public String createPlayer(@RequestParam("growth") Integer growth, @RequestParam("weight") Integer weight,
                               @RequestParam("positionId") Integer positionId,
                               @SessionAttribute("user") CreateUserDto userDto, Model model) {

        CreatePlayerDto createPlayerDto = CreatePlayerDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthDay(userDto.getBirthDay())
                .phoneNumber(phoneFormat.format(userDto.getPhoneNumber()))
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(roleService.findByName(userDto.getRole()))
                .position(positionService.findById(positionId))
                .growth(growth)
                .weight(weight)
                .build();

        ViewPlayerDto playerDto = playerService.save(createPlayerDto);
        model.addAttribute("user", userService.findByEmail(playerDto.getEmail()));

        return "status";
    }
}
