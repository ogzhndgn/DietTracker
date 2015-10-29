package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.*;
import com.diettracker.webapp.service.spec.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    FoodService foodService;
    @Autowired
    UserMealService userMealService;
    @Autowired
    MealFoodService mealFoodService;
    @Autowired
    HistoryService historyService;

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
        int id = user.getId();
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
        try {
            //TODO convert them to transactional
            UserMeal userMeal = userMealService.addUserMeal(meal, user.getId(), time);
            List<Food> foodList = this.insertFoodArray(user, foodArray);
            mealFoodService.insertNewMealFood(userMeal, foodList);
            return this.getAddMealSuccessfully(request);
        } catch (ServiceException se) {
            return this.returnProfilePageForError(se.getMessage(), user);
        }
    }

    private List<Food> insertFoodArray(User user, String[] foodArray) throws UnexpectedErrorException {
        List<Food> foodList = new ArrayList<>();
        for (String foodName : foodArray) {
            if (StringUtils.isNotBlank(foodName)) {
                Food food = foodService.insertNewFood(foodName, user.getId());
                foodList.add(food);
            }
        }
        return foodList;
    }

    private ModelAndView returnProfilePage(User user) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView("profile/profile");
        List<Meal> mealList = mealService.getMealList();
        List<History> historyList = historyService.getLasts(user.getId());
        modelAndView.addObject("historyList", historyList);
        modelAndView.addObject("user", user);
        modelAndView.addObject("mealList", mealList);
        return modelAndView;
    }

    private ModelAndView returnProfilePageForError(String apiErrorCode, User user) {
        ModelAndView modelAndView = new ModelAndView("profile/profile");
        List<Meal> mealList = null;
        List<History> historyList = null;
        try {
            mealList = mealService.getMealList();
            historyList = historyService.getLasts(user.getId());
        } catch (ServiceException ignored) {
        }
        modelAndView.addObject("historyList", historyList);
        modelAndView.addObject("mealList", mealList);
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        modelAndView.addObject("user", user);
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