package com.itacademy.web.controller;

import com.itacademy.database.dto.CreateTeamDto;
import com.itacademy.database.dto.ViewRoleDto;
import com.itacademy.database.dto.ViewTeamDto;
import com.itacademy.database.dto.ViewUserDto;
import com.itacademy.database.entity.User;
import com.itacademy.service.mapper.RoleMapper;
import com.itacademy.service.service.ManagerService;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.service.TeamService;
import com.itacademy.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateTeamController {

    private final TeamService teamService;
    private final UserService userService;
    private final RoleService roleService;
    private final PlayerService playerService;
    private final ManagerService managerService;
    private final RoleMapper roleMapper;


    @ModelAttribute
    public void setReferences(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
    }

    @GetMapping("/create-team")
    public String createTeamPage() {

        return "create-team";
    }

    @PostMapping("/create-team")
    public String createTeam(@RequestParam("name") String name, @RequestParam("userId") Long userId) {

        ViewRoleDto viewRoleDto = roleService.findByName("MANAGER");
        userService.updateRole(roleMapper.roleDtoToRole(viewRoleDto), userId);

        ViewUserDto user = userService.findById(userId);

        CreateTeamDto createTeamDto = CreateTeamDto.builder()
                .name(name)
                .manager(user)
                .build();

        ViewTeamDto viewTeamDto = teamService.save(createTeamDto);

        return "redirect:/team/" + viewTeamDto.getId();
    }

}
