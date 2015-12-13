package com.diettracker.webapp.controller.base;

import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.security.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Controller
public class BaseController {
    private static final String SESSION_VAR = "sessionInfo";
    @Autowired
    private IdGenerator idGenerator;

    protected void setSessionInfo(User user, HttpServletRequest request) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUser(user);
        sessionInfo.setSessionId(idGenerator.generateGUID());
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_VAR, sessionInfo);
    }

    protected SessionInfo getSessionInfo(HttpServletRequest request) {
        return (SessionInfo) request.getSession().getAttribute(SESSION_VAR);
    }

    protected void removeSessionInfo(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_VAR);
    }
}
