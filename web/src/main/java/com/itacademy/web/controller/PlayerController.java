package com.itacademy.web.controller;

import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerController {

    private final PlayerService playerService;
    private final PositionService positionService;

    @ModelAttribute
    public void setReferences(Model model) {
        model.addAttribute("positions", positionService.findAll());

        int[] listPageSize = {1, 5, 10, 20, 40};
        model.addAttribute("listPageSize", listPageSize);
    }

    @GetMapping("/player/{id}")
    public String getPlayer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("player", playerService.findById(id));

        return "player";
    }

    @GetMapping("/players")
    public String getPlayers() {

        return "players";
    }

    @GetMapping("/list-players")
    public String findPlayers(Model model, FilterPlayer filterPlayer,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<ViewPlayerDto> playerPage = playerService.findByFilter(filterPlayer,
                PageRequest.of(currentPage - 1, pageSize, Sort.by("lastName", "firstName")));

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("selectedPageSize", pageSize);
        model.addAttribute("filter", filterPlayer);

        int totalPages = playerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "players";
    }
}
