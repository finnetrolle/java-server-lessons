package ru.finnetrolle.jsl.service;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by finnetrolle on 15.12.2015.
 */
public class Frontend extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    private static final String KEY_PARAMETER = "key";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY_PARAMETER);
        resp.getWriter().println(StringUtils.isEmpty(key) ? "ERROR" : key);
        finalize(resp, SC_OK);
    }

    private static void finalize(HttpServletResponse response, int status) {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
    }
}
