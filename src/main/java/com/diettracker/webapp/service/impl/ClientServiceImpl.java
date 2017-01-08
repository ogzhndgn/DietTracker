package com.diettracker.webapp.service.impl;

import com.diettracker.webapp.dao.UserDao;
import com.diettracker.webapp.exception.impl.ClientListIsEmptyException;
import com.diettracker.webapp.exception.impl.InvalidDateException;
import com.diettracker.webapp.exception.impl.UnexpectedErrorException;
import com.diettracker.webapp.exception.spec.DAOException;
import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.Weight;
import com.diettracker.webapp.model.extension.Client;
import com.diettracker.webapp.service.spec.ClientService;
import com.diettracker.webapp.service.spec.WeightService;
import com.diettracker.webapp.service.util.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 8.01.2017.
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    UtilityService utilityService;
    @Autowired
    UserDao userDao;
    @Autowired
    WeightService weightService;

    @Override
    public List<Client> getClientsOfDietician(boolean isActive, int dieticianId) throws ServiceException {
        try {
            List<Client> clientList = userDao.get(isActive, dieticianId);
            if (clientList == null || clientList.isEmpty()) {
                throw new ClientListIsEmptyException();
            }
            return setClientFields(clientList);
        } catch (DAOException e) {
            throw new UnexpectedErrorException();
        }
    }

    private List<Client> setClientFields(List<Client> clientList) throws ServiceException {
        for (Client client : clientList) {
            client.setAge(this.getAgeOfUser(client.getBirthDate()));
            Weight weight = weightService.getLastWeightOfUser(client.getId());
            this.setWeightInfo(client, weight);
        }
        return clientList;
    }

    private void setWeightInfo(Client client, Weight weight) throws InvalidDateException {
        if (weight != null) {
            client.setLastWeight(String.valueOf(weight.getWeight()));
            client.setLastWeightDate(utilityService.formatDate(weight.getWeightDate()));
        } else {
            client.setLastWeight("N\\A");
            client.setLastWeightDate("N\\A");
        }
    }

    private String getAgeOfUser(Date birthDate) {
        if (birthDate == null) {
            return "N\\A";
        }
        LocalDate birthLocalDate;
        try {
            birthLocalDate = utilityService.convertToLocalDate(birthDate);
        } catch (ServiceException e) {
            return "Invalid Birth Date";
        }
        LocalDate now = LocalDate.now();
        if (birthLocalDate.isAfter(now)) {
            return "Invalid Birth Date";
        }
        Period period = Period.between(birthLocalDate, now);
        return period.getYears() + " Yıl " + period.getMonths() + " Ay";
    }
}