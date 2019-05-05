package by.itacademy.web.servlet;

import by.itacademy.database.dto.ViewUserInfoDto;
import by.itacademy.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final Integer USER_ID = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<ViewUserInfoDto> userInfoDto = UserService.getInstance().findById(USER_ID);
        userInfoDto.ifPresent(viewUserInfoDto -> req.setAttribute("user", viewUserInfoDto));

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/user.jsp")
                .forward(req, resp);
    }
}
