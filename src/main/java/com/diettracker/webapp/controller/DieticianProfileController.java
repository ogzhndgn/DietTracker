package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 13.11.2016.
 */
@Controller
public class DieticianProfileController extends BaseController {
    @RequestMapping(value = "/dietician/profile", method = RequestMethod.GET)
    public ModelAndView profileGet(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        return returnProfilePage(user);
    }

    private ModelAndView returnProfilePage(User user) {
        ModelAndView modelAndView = new ModelAndView("dietician/profile");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    private ModelAndView returnProfilePageForError(String apiErrorCode, User user) {
        ModelAndView modelAndView = new ModelAndView("dietician/profile");
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
