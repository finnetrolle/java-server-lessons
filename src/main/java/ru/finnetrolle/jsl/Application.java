package ru.finnetrolle.jsl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.finnetrolle.jsl.service.Frontend;

/**
 * Created by finnetrolle on 15.12.2015.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(frontend), "/mirror");

        server.start();
        server.join();
    }


}
