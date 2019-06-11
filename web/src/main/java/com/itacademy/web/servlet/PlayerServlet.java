package com.itacademy.web.servlet;

import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.service.service.PlayerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

    private static final Long PLAYER_ID = 3L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<ViewPlayerDto> playerDto = PlayerService.getInstance().findById(PLAYER_ID);
        playerDto.ifPresent(viewPlayerDto -> req.setAttribute("player", viewPlayerDto));

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/player.jsp")
                .forward(req, resp);
    }
}
