package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 2.05.2016.
 */
@Controller
public class MailTestPageController extends BaseController {

    @RequestMapping(value = "/forgotpassword/displaytemplate")
    public ModelAndView displayForgotPasswordMailTemplate(HttpServletRequest request) {
        return new ModelAndView("forgotpassword/mailtemplate");
    }
}