package com.example.android.vidmeclient.di.component;

import com.example.android.vidmeclient.model.IFeaturedDataSource;
import com.example.android.vidmeclient.model.IFeedDataSource;
import com.example.android.vidmeclient.model.ILogInDataSource;
import com.example.android.vidmeclient.model.INewDataSource;
import com.example.android.vidmeclient.model.IUserDataSource;

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

    IUserDataSource userDataSource();

}
