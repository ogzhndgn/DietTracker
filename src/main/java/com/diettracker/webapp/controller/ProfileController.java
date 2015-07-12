package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.Meal;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.MealService;
import com.diettracker.webapp.service.spec.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
@Controller
public class ProfileController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    MealService mealService;

    private final Logger logger = Logger.getLogger(ProfileController.class);

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profileGet(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        try {
            return returnProfilePage(user);
        } catch (ServiceException se) {
            return returnProfilePageForError(se.getMessage(), user);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = "user_update")
    public ModelAndView updateUserInfo(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String id = user.getId();
        try {
            user = userService.editUserInfo(id, name, password, confirmPassword);
            super.setSessionInfo(user, request);
        } catch (ServiceException e) {
            return this.returnProfilePageForError(e.getMessage(), user);
        }
        return this.getUserUpdatedSuccessfully(request);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = "add_meal")
    public ModelAndView addNewMeal(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        String meal = request.getParameter("meal");
        String[] foodArray = request.getParameterValues("food");
        String time = request.getParameter("time");
        logger.info("Meal: " + meal);
        for (String food : foodArray) {
            logger.info("Food: " + food);
        }
        logger.info("Time: " + time);
        return this.getAddMealSuccessfully(request);
    }

    private ModelAndView returnProfilePageForError(String apiErrorCode, User user) {
        ModelAndView modelAndView = new ModelAndView("profile/profile");
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    private ModelAndView returnProfilePage(User user) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView("profile/profile");
        List<Meal> mealList = mealService.getMealList();
        modelAndView.addObject("user", user);
        modelAndView.addObject("mealList", mealList);
        return modelAndView;
    }

    private ModelAndView getUserUpdatedSuccessfully(HttpServletRequest request) {
        ModelAndView modelAndView = this.profileGet(request);
        modelAndView.addObject("showSuccessMessage", true);
        modelAndView.addObject("successMessage", "PROFILE_UPDATED_SUCCESSFULLY");
        return modelAndView;
    }

    private ModelAndView getAddMealSuccessfully(HttpServletRequest request) {
        ModelAndView modelAndView = this.profileGet(request);
        modelAndView.addObject("showSuccessMessage", true);
        modelAndView.addObject("successMessage", "MEAL_ADDED_SUCCESSFULLY");
        return modelAndView;
    }
}