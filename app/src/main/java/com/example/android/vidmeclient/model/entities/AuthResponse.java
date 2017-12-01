package com.example.android.vidmeclient.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fbrsw on 29.11.2017.
 */

public class AuthResponse {

    @SerializedName("status")
    private Boolean status;
    @SerializedName("auth")
    private Auth auth;
    @SerializedName("user")
    private User user;
    @SerializedName("code")
    private String code;
    @SerializedName("error")
    private String error;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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
