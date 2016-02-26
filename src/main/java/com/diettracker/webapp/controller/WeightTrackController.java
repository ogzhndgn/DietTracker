package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.model.Weight;
import com.diettracker.webapp.service.spec.WeightService;
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
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.02.2016.
 */
@Controller
public class WeightTrackController extends BaseController {

    @Autowired
    WeightService weightService;

    @RequestMapping(value = "/weighttrack", method = RequestMethod.GET)
    public ModelAndView weightTrackGet(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("weighttrack/weighttrack");
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        try {
            List<Weight> weightList = weightService.getWeights(user.getId());
            modelAndView.addObject("weightList", weightList);
        } catch (ServiceException se) {
            modelAndView.addObject("showErrorMessage", true);
            modelAndView.addObject("errorMessage", se.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/weighttrack", method = RequestMethod.POST, params = "add_weight")
    public ModelAndView addWeight(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("weighttrack/weighttrack");
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        String weightDate = request.getParameter("weight-date");
        String weight = request.getParameter("weight");
        logger.info("Weight-date: " + weightDate + " weight: " + weight);
        try {
            weightService.addWeight(user.getId(), weight, weightDate);
            List<Weight> weightList = weightService.getWeights(user.getId());
            modelAndView.addObject("weightList", weightList);
        } catch (ServiceException se) {
            modelAndView.addObject("showErrorMessage", true);
            modelAndView.addObject("errorMessage", se.getMessage());
            return modelAndView;
        }
        modelAndView.addObject("showSuccessMessage", true);
        modelAndView.addObject("successMessage", "WEIGHT_SAVED_SUCCESSFULLY");
        return modelAndView;
    }

    @RequestMapping(value = "/weighttrack/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean deleteHistoryItem(@PathVariable int id, HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        User user = sessionInfo.getUser();
        try {
            weightService.deleteWeight(id, user.getId());
            return true;
        } catch (ServiceException ignored) {
            return false;
        }
    }
}