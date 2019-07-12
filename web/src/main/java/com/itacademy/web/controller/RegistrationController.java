package com.itacademy.web.controller;

import com.itacademy.database.dto.CreateUserDto;
import com.itacademy.database.dto.ViewRoleDto;
import com.itacademy.service.formatter.DateFormat;
import com.itacademy.service.formatter.PhoneFormat;
import com.itacademy.service.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SessionAttributes("user")
@RequestMapping("/registration")
public class RegistrationController {

    private final RoleService roleService;
    private final PhoneFormat phoneFormat;
    private final DateFormat dateFormat;

    @GetMapping
    public String registrationPage() {

        return "registration";
    }

    @PostMapping
    public String registration(CreateUserDto userDto, Model model) {
        userDto.setPhoneNumber(phoneFormat.format(userDto.getPhoneNumber()));
        userDto.setBirthDay(dateFormat.format(userDto.getBirthDay())
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        model.addAttribute("user", userDto);

        ViewRoleDto roleDto = roleService.findByName(userDto.getRole());
        if ("PLAYER".equals(roleDto.getName())) {
            return "redirect:/create-player";
        } if ("LANDLORD".equals(roleDto.getName())){
            return "redirect:/create-field";
        }

        return "players";
    }
}
