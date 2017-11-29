package com.example.android.vidmeclient.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fbrsw on 29.11.2017.
 */

public class AuthResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("auth")
    @Expose
    private Auth auth;
    @SerializedName("user")
    @Expose
    private User user;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
