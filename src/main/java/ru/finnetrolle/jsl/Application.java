package ru.finnetrolle.jsl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.finnetrolle.jsl.service.AuthService;

import ru.finnetrolle.jsl.servlet.SignInServlet;
import ru.finnetrolle.jsl.servlet.SignUpServlet;

/**
 * Created by finnetrolle on 15.12.2015.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);

        context.addServlet(new ServletHolder(new SignInServlet(AuthService.getInstance())), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(AuthService.getInstance())), "/signup");

        server.start();
        server.join();
    }


}
