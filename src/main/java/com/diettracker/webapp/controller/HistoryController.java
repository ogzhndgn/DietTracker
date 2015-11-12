package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.History;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.HistoryService;
import com.diettracker.webapp.service.spec.MealFoodService;
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

    @RequestMapping(value = "/history", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showHistroy(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("history/history");
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        try {
            List<History> historyList = historyService.getAll(user.getId());
            modelAndView.addObject("showErrorMessage", false);
            modelAndView.addObject("historyList", historyList);
            return modelAndView;
        } catch (ServiceException e) {
            return returnHistoryErrorPage(modelAndView, e.getMessage());
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

    private ModelAndView returnHistoryErrorPage(ModelAndView modelAndView, String apiErrorCode) {
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
        return modelAndView;
    }
}