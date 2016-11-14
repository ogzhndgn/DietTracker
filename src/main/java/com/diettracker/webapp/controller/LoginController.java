package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.enums.Role;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.UserService;
import com.diettracker.webapp.service.util.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 18.6.2015
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    FormValidator formValidator;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loginGet() {
        logger.info("Login form loaded...");
        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("showRegisterForm", false);
        modelAndView.addObject("showLoginForm", true);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView loginPost(HttpServletRequest request) {
        try {
            logger.info("Login attempt for " + request.getParameter("email"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = userService.loginUser(email, password);
            super.setSessionInfo(user, request);
            return getModelAndView(user);
        } catch (ServiceException e) {
            logger.error("Invalid login for " + request.getParameter("email"));
            return this.returnLoginForm(e.getMessage());
        }
    }

    private ModelAndView getModelAndView(User user) {
        if (user.getRole() == Role.CLIENT) {
            return new ModelAndView("redirect:/profile");
        }
        return new ModelAndView("redirect:/dietician/profile");
    }

    private ModelAndView returnLoginForm(String apiErrorCode) {
        ModelAndView mav = new ModelAndView("login/login");
        mav.addObject("errorMessage", apiErrorCode);
        mav.addObject("showRegisterForm", false);
        mav.addObject("showLoginForm", true);
        mav.addObject("showErrorMessage", true);
        return mav;
    }
}