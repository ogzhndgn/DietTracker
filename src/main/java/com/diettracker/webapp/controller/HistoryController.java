package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.History;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.HistoryService;
import com.diettracker.webapp.service.spec.MealFoodService;
import com.diettracker.webapp.service.spec.MealService;
import com.diettracker.webapp.service.spec.UserMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 30.7.2015
 */
@Controller
public class HistoryController extends BaseController {

    @Autowired
    HistoryService historyService;
    @Autowired
    UserMealService userMealService;
    @Autowired
    MealFoodService mealFoodService;
    @Autowired
    MealService mealService;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView showHistroy(HttpServletRequest request) throws UnexpectedErrorException {
        ModelAndView modelAndView = new ModelAndView("history/history");
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        List<History> historyList = historyService.getAll(user.getId());
        try {
            modelAndView.addObject("historyList", historyList);
            modelAndView.addObject("mealList", mealService.getMealList());
            modelAndView.addObject("showErrorMessage", false);
            return modelAndView;
        } catch (ServiceException e) {
            return this.returnHistoryErrorPage(e.getMessage());
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public ModelAndView searchForMeal(HttpServletRequest request) throws UnexpectedErrorException {
        ModelAndView modelAndView = new ModelAndView("history/history");
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        String mealId = request.getParameter("meal");
        String mealTimeBegin = request.getParameter("meal-time-begin");
        String mealTimeEnd = request.getParameter("meal-time-end");
        String foodSearch = request.getParameter("food-search");
        try {
            List<History> historyList = historyService.search(mealId, mealTimeBegin, mealTimeEnd, foodSearch, user.getId());
            modelAndView.addObject("historyList", historyList);
            modelAndView.addObject("mealList", mealService.getMealList());
            modelAndView.addObject("showErrorMessage", false);
            modelAndView.addObject("mealId", mealId);
            modelAndView.addObject("mealTimeBegin", mealTimeBegin);
            modelAndView.addObject("mealTimeEnd", mealTimeEnd);
            modelAndView.addObject("foodSearch", foodSearch);
            return modelAndView;
        } catch (ServiceException e) {
            return this.returnHistoryErrorPage(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean deleteHistoryItem(@PathVariable int id, HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        try {
            userMealService.getUserMeal(id, user.getId());
            mealFoodService.deleteMealFood(id);
            userMealService.deleteUserMeal(id, user.getId());
            return true;
        } catch (ServiceException ignored) {
            return false;
        }
    }

    private ModelAndView returnHistoryErrorPage(String apiErrorCode) {
        ModelAndView modelAndView = new ModelAndView("history/history");
        try {
            modelAndView.addObject("mealList", mealService.getMealList());
        } catch (ServiceException ignored) {
        }
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        return modelAndView;
    }
}