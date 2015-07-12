package com.diettracker.webapp.model;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 4.7.2015
 */
public class SessionInfo {
    private User user;
    private String sessionId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
