package com.diettracker.webapp.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> on 20.02.2016.
 */
public class Weight {
    private int id;
    private int userId;
    private double weight;
    private Date weightDate;
    private Timestamp createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
