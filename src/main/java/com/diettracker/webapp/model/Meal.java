package com.diettracker.webapp.model;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 6.7.2015
 */
public class Meal {
    private int id;
    private String code;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
