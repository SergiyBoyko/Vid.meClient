package com.example.android.vidmeclient.model.prefs;

import android.content.SharedPreferences;

import com.example.android.vidmeclient.model.IUserDataSource;

/**
 * Created by fbrsw on 01.12.2017.
 */

public class UserDataSource extends BasePrefSource implements IUserDataSource {

    public static final String AUTHORIZED = "authorized";
    public static final String AUTH_TOKEN = "auth_token";

    public UserDataSource(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    @Override
    public String getToken() {
        return getStringPreference(AUTH_TOKEN);
    }

    @Override
    public void setToken(String token) {
        setStringPreference(AUTH_TOKEN, token);
    }

    @Override
    public boolean isAuthorized() {
        return getBooleanPreference(AUTHORIZED, false);
    }

    @Override
    public void setAuthorized() {
        setBooleanPreference(AUTHORIZED, true);
    }

    @Override
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
