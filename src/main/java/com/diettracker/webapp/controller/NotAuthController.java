package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by the Poet on 29.10.2015.
 */
@Controller
public class NotAuthController extends BaseController {

    @RequestMapping(value = "/notauth", method = RequestMethod.GET)
    public ModelAndView notAuth(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("showRegisterForm", false);
        modelAndView.addObject("showLoginForm", true);
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", "NOT_AUTH_TO_SEE");
        return modelAndView;
    }
}
