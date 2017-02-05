package com.eselman.medisys.entities;

import java.io.Serializable;

/**
 * Created by eselman on 04/02/2017.
 */

public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
