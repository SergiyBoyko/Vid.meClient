package com.example.android.vidmeclient.di.component;

import com.example.android.vidmeclient.model.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.model.remote.IFeedDataSource;
import com.example.android.vidmeclient.model.remote.ILogInDataSource;
import com.example.android.vidmeclient.model.remote.INewDataSource;

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
