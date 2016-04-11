package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.PasswordRecovery;
import com.diettracker.webapp.service.spec.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 12.04.2016.
 */
@Controller
public class PasswordRecoveryController extends BaseController {
    @Autowired
    PasswordRecoveryService passwordRecoveryService;

    @RequestMapping(value = "/passwordrecovery/{hash}", method = RequestMethod.GET)
    public ModelAndView passwordRecoveryGet(@PathVariable("hash") String hash, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("passwordrecovery/passwordrecovery");
        try {
            PasswordRecovery passwordRecovery = passwordRecoveryService.getByHash(hash);
            modelAndView.addObject("hash", passwordRecovery.getHash());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/passwordrecovery/{hash}", method = RequestMethod.POST)
    public ModelAndView passwordRecoveryPost(@PathVariable("hash") String hash, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("passwordrecovery/passwordrecovery");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        System.out.println(password + " - " + confirmPassword);
        return modelAndView;
    }
}