package com.example.android.vidmeclient.di.module;


import com.example.android.vidmeclient.Constants;
import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.module.remote.FeaturedDataSource;
import com.example.android.vidmeclient.module.remote.FeedDataSource;
import com.example.android.vidmeclient.module.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.module.remote.IFeedDataSource;
import com.example.android.vidmeclient.module.remote.ILogInDataSource;
import com.example.android.vidmeclient.module.remote.INewDataSource;
import com.example.android.vidmeclient.module.remote.LogInDataSource;
import com.example.android.vidmeclient.module.remote.NewDataSource;

import javax.inject.Named;
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
        return new FeaturedDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    INewDataSource provideNewDataSource(Retrofit retrofit) {
        return new NewDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    IFeedDataSource provideFeedDataSource(Retrofit retrofit) {
        return new FeedDataSource(retrofit.create(VidMeApi.class));
    }

    @Provides
    @Singleton
    ILogInDataSource provideLogInDataSource(Retrofit retrofit) {
        return new LogInDataSource(retrofit.create(VidMeApi.class));
    }

}
