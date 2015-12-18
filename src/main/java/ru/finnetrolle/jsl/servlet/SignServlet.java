package ru.finnetrolle.jsl.servlet;

import ru.finnetrolle.jsl.service.AuthService;

import javax.servlet.http.HttpServlet;

/**
 * Created by finnetrolle on 19.12.2015.
 */
public abstract class SignServlet extends HttpServlet {

    protected final AuthService authService;

    protected static final String LOGIN_FIELD_NAME = "login";
    protected static final String PASSWORD_FIELD_NAME = "password";
    protected static final String UNAUTHORIZED_MESSAGE = "Unauthorized";
    protected static final String AUTHORIZED_MESSAGE = "Authorized";
    protected static final String CONTENT_TYPE = "text/html; charset=utf-8";

    protected SignServlet(AuthService authService) {
        this.authService = authService;
    }

}
