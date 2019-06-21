package com.itacademy.web.servlet;

import com.itacademy.database.filter.FilterPlayer;
import com.itacademy.database.dto.ViewPlayerDto;
import com.itacademy.database.dto.ViewPositionDto;
import com.itacademy.service.service.PlayerService;
import com.itacademy.service.service.PositionService;
import com.itacademy.service.util.JspPathUtil;
import com.itacademy.web.config.WebConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-players")
public class ListPlayersServlet extends HttpServlet {

    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebConfig.class);

    private PositionService positionService = applicationContext.getBean(PositionService.class);
    private PlayerService playerService = applicationContext.getBean(PlayerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ViewPositionDto> positions = positionService.findAll();
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

        List<ViewPlayerDto> players = playerService.findByFilter(filterPlayer);
        req.setAttribute("players", players);

        List<ViewPositionDto> positions = positionService.findAll();
        req.setAttribute("positions", positions);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("list-players"))
                .forward(req, resp);
    }
}
