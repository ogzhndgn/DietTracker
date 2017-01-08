package com.diettracker.webapp.model.extension;

import com.diettracker.webapp.model.User;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 8.01.2017.
 */
public class Client extends User {
    private String age;
    private String lastWeight;
    private String lastWeightDate;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(String lastWeight) {
        this.lastWeight = lastWeight;
    }

    public String getLastWeightDate() {
        return lastWeightDate;
    }

    public void setLastWeightDate(String lastWeightDate) {
        this.lastWeightDate = lastWeightDate;
    }
}