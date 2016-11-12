package com.diettracker.webapp.enums;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.09.2016.
 */
public enum Role {
    /**
     * Admin is the person who see the whole system. Relation between dieticians and clients, reports etc.
     */
    ADMIN,
    /**
     * Dietician is the person who is the doctor and has some clients give diet menus and checks them regularly
     */
    DIETICIAN,
    /**
     * Client is the basic person, main user in the system. They use the system to save their meal, food, weight history for their dieticians if they have
     */
    CLIENT
}