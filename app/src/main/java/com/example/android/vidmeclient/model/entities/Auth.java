package com.example.android.vidmeclient.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fbrsw on 29.11.2017.
 */

public class Auth {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expires")
    @Expose
    private String expires;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
