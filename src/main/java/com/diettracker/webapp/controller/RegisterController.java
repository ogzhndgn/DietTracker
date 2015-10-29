package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 21.6.2015
 */
@Controller
public class RegisterController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(HttpServletRequest request) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm-password");
            User user = userService.registerUser(email, password, confirmPassword);
            super.setSessionInfo(user, request);
            return new ModelAndView("redirect:/profile");
        } catch (ServiceException e) {
            return this.returnToRegisterForm(e.getMessage());
        }
    }

    private ModelAndView returnToRegisterForm(String apiErrorCode) {
        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("showRegisterForm", true);
        modelAndView.addObject("showLoginForm", false);
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        return modelAndView;
    }
}