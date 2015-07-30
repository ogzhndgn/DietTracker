package com.diettracker.webapp.controller;

import com.diettracker.webapp.controller.base.BaseController;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.History;
import com.diettracker.webapp.model.SessionInfo;
import com.diettracker.webapp.model.User;
import com.diettracker.webapp.service.spec.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private ModelAndView returnHistoryErrorPage(ModelAndView modelAndView, String apiErrorCode) {
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("errorMessage", apiErrorCode);
//        modelAndView.addObject("historyList", new ArrayList<>());
        return modelAndView;
    }
}
