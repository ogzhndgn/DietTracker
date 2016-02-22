package com.diettracker.webapp.interceptor;

import com.diettracker.webapp.model.SessionInfo;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 28.08.2015
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = org.apache.logging.log4j.LogManager.getLogger(SessionInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("notauth");
        }
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
        if (sessionInfo == null) {
            response.sendRedirect("notauth");
        }
        logger.info("request.getServletPath(): " + request.getServletPath());
        return true;
    }
}