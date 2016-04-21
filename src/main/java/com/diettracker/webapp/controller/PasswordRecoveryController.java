package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.PasswordRecovery;
import com.diettracker.webapp.service.spec.PasswordRecoveryService;
import com.diettracker.webapp.service.spec.UserService;
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
    @Autowired
    UserService userService;

    @RequestMapping(value = "/passwordrecovery/{hash}", method = RequestMethod.GET)
    public ModelAndView passwordRecoveryGet(@PathVariable("hash") String hash, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("passwordrecovery/passwordrecovery");
        try {
            PasswordRecovery passwordRecovery = passwordRecoveryService.getByHash(hash);
            modelAndView.addObject("hash", passwordRecovery.getHash());
        } catch (ServiceException e) {
            return new ModelAndView("common/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/passwordrecovery/{hash}", method = RequestMethod.POST)
    public ModelAndView passwordRecoveryPost(@PathVariable("hash") String hash, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("passwordrecovery/passwordrecovery");
        try {
            PasswordRecovery passwordRecovery = passwordRecoveryService.getByHash(hash);
            int userId = passwordRecovery.getUserId();
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm-password");
            userService.recoverPassword(userId, password, confirmPassword);
            if (!passwordRecoveryService.updateAfterUse(passwordRecovery.getId())) {
                logger.info("Password recovery can not be updated for hash " + hash);
            }
            modelAndView.addObject("showSuccessMessage", true);
            modelAndView.addObject("successMessage", "PASSWORD_CHANGED_SUCCESSFULLY");
        } catch (ServiceException e) {
            modelAndView.addObject("showErrorMessage", true);
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        return modelAndView;
    }
}