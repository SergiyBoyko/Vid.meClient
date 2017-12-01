package com.example.android.vidmeclient;

import android.support.multidex.MultiDexApplication;

import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerAppComponent;
import com.example.android.vidmeclient.di.module.ApiModule;
import com.example.android.vidmeclient.di.module.AppModule;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class AppVidMe extends MultiDexApplication {
    private AppComponent appComponent;

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

}
