package com.diettracker.webapp.model;

import com.diettracker.webapp.enums.Role;

import java.util.Date;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 20.6.2015
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private String passwordSalt;
    private boolean active;
    private String password;
    private Role role;
    private int dieticianId;
    private Date birthDate;
    private String age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getDieticianId() {
        return dieticianId;
    }

    public void setDieticianId(int dieticianId) {
        this.dieticianId = dieticianId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate != null) {
            this.birthDate = new java.util.Date(birthDate.getTime());
        } else {
            this.birthDate = birthDate;
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", dieticianId=" + dieticianId +
                ", birthDate=" + birthDate +
                '}';
    }
}