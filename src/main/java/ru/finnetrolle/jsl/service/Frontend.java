package ru.finnetrolle.jsl.service;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by finnetrolle on 15.12.2015.
 */
public class Frontend extends HttpServlet {

    private static final String S_METHOD = "Method";
    private static final String S_URL = "URL";
    private static final String S_PATH_INFO = "PathInfo";
    private static final String S_SESSION_ID = "SessionId";
    private static final String S_PARAMETERS = "Parameters";
    private static final String S_MESSAGE = "message";

    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    private static final String TEMPLATE_FILE = "template.html";

    private static final String KEY_PARAMETER = "key";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter(KEY_PARAMETER);
        resp.getWriter().println(StringUtils.isEmpty(key) ? "ERROR" : key);
        finalize(resp, SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = getHeadersMap.apply(req);

        String message = req.getParameter(S_MESSAGE);

        pageVariables.put(S_MESSAGE, (message == null) ? "" : message);
        resp.getWriter().println(PageGenerator.instance().getPage(TEMPLATE_FILE, pageVariables));

        if (StringUtils.isEmpty(message)) {
            finalize(resp, SC_FORBIDDEN);
        } else {
            finalize(resp, SC_OK);
        }
    }

    private static void finalize(HttpServletResponse response, int status) {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
    }

    private static Function<HttpServletRequest, Map<String, Object>> getHeadersMap = x -> {
        Map<String, Object> result = new HashMap<>();
        result.put(S_METHOD, x.getMethod());
        result.put(S_URL, x.getRequestURL().toString());
        result.put(S_PATH_INFO, StringUtils.isEmpty(x.getPathInfo()) ? "" : x.getPathInfo());
        result.put(S_SESSION_ID, x.getSession().getId());
        result.put(S_PARAMETERS, x.getParameterMap().toString());
        return result;
    };
}
