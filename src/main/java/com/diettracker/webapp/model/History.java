package com.diettracker.webapp.model;

import java.sql.Timestamp;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 22.7.2015
 */
public class History {
    private int id;
    private String code;
    private Timestamp eatingTime;
    private String foodList;
    private int userId;

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

    public Timestamp getEatingTime() {
        return eatingTime;
    }

    public void setEatingTime(Timestamp eatingTime) {
        this.eatingTime = eatingTime;
    }

    public String getFoodList() {
        return foodList;
    }

    public void setFoodList(String foodList) {
        this.foodList = foodList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}