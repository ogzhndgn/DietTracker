package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.enums.Role;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.model.extension.Client;
import com.diettracker.webapp.service.spec.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 14.11.2016.
 */
@Controller
public class ClientsController extends BaseController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/dietician/clients", method = RequestMethod.GET)
    public ModelAndView clientsListGet(HttpServletRequest request) {
        User user = super.getSessionInfo(request).getUser();
        ModelAndView modelAndView = new ModelAndView("dietician/clients/clients");
        modelAndView.addObject("user", user);
        try {
            super.checkPermissionForRole(user.getRole(), new Role[]{Role.DIETICIAN});
            List<Client> clientList = clientService.getClientsOfDietician(true, user.getId());
            modelAndView.addObject("clientList", clientList);
        } catch (ServiceException se) {
            modelAndView.addObject("showErrorMessage", true);
            modelAndView.addObject("errorMessage", se.getMessage());
        }
        return modelAndView;
    }
}