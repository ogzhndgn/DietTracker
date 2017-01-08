package com.diettracker.webapp.service.spec;

import com.diettracker.webapp.exception.spec.ServiceException;
import com.diettracker.webapp.model.extension.Client;

import java.util.List;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 8.01.2017.
 */
public interface ClientService {
    List<Client> getClientsOfDietician(boolean isActive, int dieticianId) throws ServiceException;
}