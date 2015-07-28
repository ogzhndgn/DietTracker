package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 29.7.2015
 */
@Controller
public class LogOutController extends BaseController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        super.removeSessionInfo(request);
        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("showRegisterForm", false);
        modelAndView.addObject("showLoginForm", true);
        modelAndView.addObject("showLogOutMessage", true);
        modelAndView.addObject("successMessage", "LOG_OUT_SUCCESSFULLY");
        return modelAndView;
    }
}