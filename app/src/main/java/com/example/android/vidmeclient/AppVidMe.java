package com.example.android.vidmeclient;

import android.support.multidex.MultiDexApplication;

import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerAppComponent;
import com.example.android.vidmeclient.di.module.ApiModule;
import com.example.android.vidmeclient.di.module.AppModule;
import com.example.android.vidmeclient.model.entities.AuthResponse;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class AppVidMe extends MultiDexApplication {
    private AppComponent appComponent;
    private AuthResponse userProfile;

    public AppVidMe() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

    public AuthResponse getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(AuthResponse userProfile) {
        this.userProfile = userProfile;
    }
}
