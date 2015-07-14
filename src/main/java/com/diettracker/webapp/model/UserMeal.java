package com.diettracker.webapp.model;

import java.sql.Timestamp;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 15.7.2015
 */
public class UserMeal {
    private int id;
    private int mealId;
    private int userId;
    private Timestamp eatingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getEatingTime() {
        return eatingTime;
    }

    public void setEatingTime(Timestamp eatingTime) {
        this.eatingTime = eatingTime;
    }
}