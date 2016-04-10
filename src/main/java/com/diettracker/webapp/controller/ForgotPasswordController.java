package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.PasswordRecovery;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.api.MailSenderService;
import com.diettracker.webapp.service.api.RandomOrgService;
import com.diettracker.webapp.service.security.HashService;
import com.diettracker.webapp.service.spec.PasswordRecoveryService;
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
    @Autowired
    RandomOrgService randomOrgService;
    @Autowired
    HashService hashService;
    @Autowired
    PasswordRecoveryService passwordRecoveryService;

    @RequestMapping(value = "/forgotpassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forgotPasswordGet(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (StringUtils.isNotBlank(email)) {
            this.sendForgotPasswordMail(email);
        }
        return new ModelAndView("forgotpassword/forgotpassword");
    }

    private void sendForgotPasswordMail(String email) {
        try {
            User user = userService.getByEmail(email);
            String token = randomOrgService.getGeneratedToken();
            String hash = hashService.hashData(token + user.getEmail());
            PasswordRecovery passwordRecovery = passwordRecoveryService.add(user.getId(), token, hash);
            mailSenderService.sendForgotPasswordMail(user.getEmail(), passwordRecovery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}