package com.example.android.vidmeclient.di.module;


import android.content.SharedPreferences;

import com.example.android.vidmeclient.common.Constants;
import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.IUserDataSource;
import com.example.android.vidmeclient.model.prefs.UserDataSource;
import com.example.android.vidmeclient.model.remote.FeaturedRemoteDataSource;
import com.example.android.vidmeclient.model.remote.FeedRemoteDataSource;
import com.example.android.vidmeclient.model.IFeaturedDataSource;
import com.example.android.vidmeclient.model.IFeedDataSource;
import com.example.android.vidmeclient.model.ILogInDataSource;
import com.example.android.vidmeclient.model.INewDataSource;
import com.example.android.vidmeclient.model.remote.LogInRemoteDataSource;
import com.example.android.vidmeclient.model.remote.NewRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fbrsw on 27.11.2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.VID_ME_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    IFeaturedDataSource provideFeaturedDataSource(Retrofit retrofit) {
        return new FeaturedRemoteDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    INewDataSource provideNewDataSource(Retrofit retrofit) {
        return new NewRemoteDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    IFeedDataSource provideFeedDataSource(Retrofit retrofit) {
        return new FeedRemoteDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    ILogInDataSource provideLogInDataSource(Retrofit retrofit) {
        return new LogInRemoteDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    IUserDataSource provideUserDataSource(SharedPreferences preferences) {
        return new UserDataSource(preferences);
    }

}
