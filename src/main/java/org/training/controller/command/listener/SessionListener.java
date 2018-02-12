package org.training.controller.command.listener;

import org.training.constant.jsp.RequestAttributes;
import org.training.entity.full.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.time.LocalDate;
import java.util.List;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(10);

        System.out.println("session created" + LocalDate.now());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String userEmail = ((User)httpSessionEvent.getSession().getAttribute(RequestAttributes.USER)).getEmail();
        List<String> loggedUsers = (List<String>) httpSessionEvent.getSession().getServletContext()
                .getAttribute(RequestAttributes.LOGGED_USERS);
        loggedUsers.remove(userEmail);
        httpSessionEvent.getSession().getServletContext().setAttribute(RequestAttributes.LOGGED_USERS, loggedUsers);

        System.out.println("session destroyed" + LocalDate.now());
    }
}
