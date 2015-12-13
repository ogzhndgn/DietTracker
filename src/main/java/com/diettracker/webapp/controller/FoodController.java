package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.impl.FoodNotFoundException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.model.Food;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */

@Controller
public class FoodController extends BaseController {
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/getfood", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFoodByPrefix(@RequestParam(value = "prefix") String prefix, HttpServletRequest request) {
        int userId = this.getUserId(request);
        List<Food> foodList = new ArrayList<>();
        foodList.add(this.createFoodFromPrefix(prefix, userId));
        try {
            foodList.addAll(foodService.getByPrefix(prefix, userId));
        } catch (FoodNotFoundException | UnexpectedErrorException ignored) {
        }
        return this.getFoodNameList(foodList);
    }

    @RequestMapping(value = "/getallfood", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllFood(HttpServletRequest request) {
        int userId = this.getUserId(request);
        List<Food> foodList = new ArrayList<>();
        try {
            foodList = foodService.getAllFoodByUser(userId);
        } catch (FoodNotFoundException | UnexpectedErrorException ignored) {
        }
        return this.getFoodNameList(foodList);
    }

    private Food createFoodFromPrefix(String prefix, int userId) {
        Food tempFood = new Food();
        tempFood.setId(0);
        tempFood.setName(prefix);
        tempFood.setUserId(userId);
        return tempFood;
    }

    private int getUserId(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        return user.getId();
    }

    private List<String> getFoodNameList(List<Food> foodList) {
        List<String> foodNameList = new ArrayList<>();
        for (Food food : foodList) {
            foodNameList.add(food.getName());
        }
        return foodNameList;
    }
}