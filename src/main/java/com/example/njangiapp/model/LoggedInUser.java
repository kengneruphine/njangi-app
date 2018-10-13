package com.example.njangiapp.model;

public class LoggedInUser {
    private UserSys userSys;
    private String token;

    public LoggedInUser(UserSys userSys, String token) {
        super();
        this.userSys = userSys;
        this.token = token;
    }

    public UserSys getUserSys() {
        return this.userSys;
    }

    public void setUserSys(UserSys userSys) {
        this.userSys = userSys;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
