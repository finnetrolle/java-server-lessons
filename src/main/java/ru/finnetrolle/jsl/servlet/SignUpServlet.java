package ru.finnetrolle.jsl.servlet;

import ru.finnetrolle.jsl.model.User;
import ru.finnetrolle.jsl.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by finnetrolle on 19.12.2015.
 */
public class SignUpServlet extends SignServlet {

    public SignUpServlet(AuthService authService) {
        super(authService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_FIELD_NAME);
        String password = req.getParameter(PASSWORD_FIELD_NAME);
        resp.setContentType(CONTENT_TYPE);

        User user = authService.register(login, password);
        if (user == null) {
            resp.setStatus(SC_CONFLICT);
        } else {
            resp.setStatus(SC_OK);
        }
    }
}
