package com.example.android.vidmeclient.di.component;

import com.example.android.vidmeclient.module.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.module.remote.IFeedDataSource;
import com.example.android.vidmeclient.module.remote.ILogInDataSource;
import com.example.android.vidmeclient.module.remote.INewDataSource;

import javax.inject.Named;

import retrofit2.Retrofit;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface ApiComponent {

    Retrofit retrofit();

    IFeaturedDataSource featuredVideosDataSource();

    INewDataSource newVideosDataSource();

    IFeedDataSource feedVideosDataSource();

    ILogInDataSource logInDataSource();

}
