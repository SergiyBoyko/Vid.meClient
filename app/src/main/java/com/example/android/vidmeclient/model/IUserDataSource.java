package com.example.android.vidmeclient.model;

/**
 * Created by fbrsw on 01.12.2017.
 */

public interface IUserDataSource {

    String getToken();

    void setToken(String token);

    boolean isAuthorized();

    void setAuthorized();

    void clear();
}
