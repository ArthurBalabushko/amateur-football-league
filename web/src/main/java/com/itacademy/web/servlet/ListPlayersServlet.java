package com.itacademy.web.servlet;

import com.itacademy.database.Filter.FilterPlayer;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.dto.ViewPositionDto;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.PositionService;
import com.itacademy.service.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-players")
public class ListPlayersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ViewPositionDto> positions = PositionService.getInstance().findAll();
        req.setAttribute("positions", positions);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("list-players"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int offset = 0;
        int limit = Integer.valueOf(req.getParameter("limit"));

        if (Integer.valueOf(req.getParameter("page")) > 1) {
            offset = (Integer.valueOf(req.getParameter("page")) * limit) - limit;
        }

        FilterPlayer filterPlayer = FilterPlayer.builder()
                .position(req.getParameter("position"))
                .team(req.getParameter("team"))
                .ageFrom(Integer.valueOf(req.getParameter("ageFrom")))
                .ageTo(Integer.valueOf(req.getParameter("ageTo")))
                .limit(Integer.valueOf(req.getParameter("limit")))
                .offset(offset)
                .build();

        List<ViewPlayerDto> players = PlayerService.getInstance().findAllByFilter(filterPlayer);
        req.setAttribute("players", players);

        List<ViewPositionDto> positions = PositionService.getInstance().findAll();
        req.setAttribute("positions", positions);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("list-players"))
                .forward(req, resp);
    }
}
