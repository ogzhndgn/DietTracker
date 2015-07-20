package com.diettracker.webapp.model;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.7.2015
 */
public class MealFood {
    private int id;
    private int mealId;
    private int foodId;

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

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}