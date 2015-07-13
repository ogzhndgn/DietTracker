package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.Food;
import com.diettracker.webapp.model.Meal;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.FoodService;
import com.diettracker.webapp.service.spec.MealService;
import com.diettracker.webapp.service.spec.UserService;
import org.apache.log4j.Logger;
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
            logger.info("Meal: " + meal);
            List<Food> foodList = this.insertFoodArray(user, foodArray);
            for (Food food : foodList) {
                logger.info(food.getId() + " " + food.getName());
            }
            logger.info("Time: " + time);
            return this.getAddMealSuccessfully(request);
        } catch (ServiceException se) {
            return this.returnProfilePageForError(se.getMessage(), user);
        }
    }

    private List<Food> insertFoodArray(User user, String[] foodArray) throws UnexpectedErrorException {
        List<Food> foodList = new ArrayList<>();
        for (String foodName : foodArray) {
            Food food = foodService.insertNewFood(foodName, user.getId());
            foodList.add(food);
            logger.info("Food: " + foodName);
        }
        return foodList;
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