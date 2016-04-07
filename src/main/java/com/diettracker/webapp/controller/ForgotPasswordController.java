package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.MailSenderService;
import com.diettracker.webapp.service.spec.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.03.2016.
 */
@Controller
public class ForgotPasswordController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    MailSenderService mailSenderService;

    @RequestMapping(value = "/forgotpassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forgotPasswordGet(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (StringUtils.isNotBlank(email)) {
            try {
                User user = userService.getByEmail(email);
                mailSenderService.send(user.getEmail(), "Kola nerde geeyy");
            } catch (ServiceException | IOException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("forgotpassword/forgotpassword");
    }
}