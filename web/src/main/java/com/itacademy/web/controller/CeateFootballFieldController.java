package com.itacademy.web.controller;

import com.itacademy.database.dto.CreateFootballFieldDto;
import com.itacademy.database.dto.CreateLandlordDto;
import com.itacademy.database.dto.CreateUserDto;
import com.itacademy.database.dto.ViewLandlordDto;
import com.itacademy.service.formatter.PhoneFormat;
import com.itacademy.service.service.FootballFieldService;
import com.itacademy.service.service.LandlordService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CeateFootballFieldController {

    private final LandlordService landlordService;
    private final FootballFieldService footballFieldService;
    private final UserService userService;
    private final RoleService roleService;
    private final PhoneFormat phoneFormat;

    @GetMapping("/create-field")
    public String createPlayerPage() {

        return "create-field";
    }

    @PostMapping("/create-field")
    public String createPlayer(@SessionAttribute("user") CreateUserDto userDto,
                               CreateFootballFieldDto footballField,
                               Model model) {

        CreateLandlordDto createLandlordDto = CreateLandlordDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthDay(userDto.getBirthDay())
                .phoneNumber(phoneFormat.format(userDto.getPhoneNumber()))
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(roleService.findByName(userDto.getRole()))
                .build();

        ViewLandlordDto landlordDto = landlordService.save(createLandlordDto);

        footballField.setLandlord(landlordDto);
        footballFieldService.save(footballField);

        model.addAttribute("user", userService.findByEmail(landlordDto.getEmail()));

        return "status";
    }
}
