package com.example.android.vidmeclient.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.vidmeclient.di.module.ApiModule;
import com.example.android.vidmeclient.di.module.AppModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fbrsw on 27.11.2017.
 */


@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent {

    Context context();

    SharedPreferences preferences();

    Executor executor();

}
