package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.enums.Role;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 25.12.2016.
 */
@Controller
@RequestMapping(value = "/dietician")
public class ClientDetailController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/clientdetail/{clientId}", method = RequestMethod.GET)
    public ModelAndView clientDetailGet(HttpServletRequest request, @PathVariable("clientId") int clientId) {
        User user = super.getSessionInfo(request).getUser();
        ModelAndView modelAndView = new ModelAndView("dietician/clients/client-detail");
        try {
            super.checkPermissionForRole(user.getRole(), new Role[]{Role.DIETICIAN});
            User client = userService.getDieticiansClient(user.getId(), clientId);
            modelAndView.addObject("client", client);
        } catch (ServiceException se) {
            modelAndView.addObject("showErrorMessage", true);
            modelAndView.addObject("errorMessage", se.getMessage());
        }
        return modelAndView;
    }
}
