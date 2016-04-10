package com.diettracker.webapp.model;


import com.diettracker.webapp.enums.PasswordRecoveryStatus;

import java.sql.Timestamp;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
public class PasswordRecovery {
    private int id;
    private int userId;
    private String token;
    private String hash;
    private Timestamp createdTime;
    private Timestamp expirationTime;
    private Timestamp usingTime;
    private PasswordRecoveryStatus status;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Timestamp getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(Timestamp usingTime) {
        this.usingTime = usingTime;
    }

    public PasswordRecoveryStatus getStatus() {
        return status;
    }

    public void setStatus(PasswordRecoveryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PasswordRecovery{" +
                "id=" + id +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", hash='" + hash + '\'' +
                ", createdTime=" + createdTime +
                ", expirationTime=" + expirationTime +
                ", usingTime=" + usingTime +
                ", status=" + status +
                '}';
    }
}