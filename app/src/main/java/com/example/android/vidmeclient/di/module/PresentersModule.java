package com.example.android.vidmeclient.di.module;

import com.example.android.vidmeclient.di.scope.Scope;
import com.example.android.vidmeclient.di.scope.Scopes;
import com.example.android.vidmeclient.model.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.model.remote.IFeedDataSource;
import com.example.android.vidmeclient.model.remote.ILogInDataSource;
import com.example.android.vidmeclient.model.remote.INewDataSource;
import com.example.android.vidmeclient.presenters.FeaturedDataPresenter;
import com.example.android.vidmeclient.presenters.FeedDataPresenter;
import com.example.android.vidmeclient.presenters.LogInPresenter;
import com.example.android.vidmeclient.presenters.NewDataPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fbrsw on 27.11.2017.
 */

@Module
public class PresentersModule {

    @Provides
    @Scope(Scopes.VIEW)
    public FeaturedDataPresenter provideFeaturedDataPresenter(IFeaturedDataSource featuredDataSource) {
        return new FeaturedDataPresenter(featuredDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public NewDataPresenter provideNewDataPresenter(INewDataSource newDataSource) {
        return new NewDataPresenter(newDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public FeedDataPresenter provideFeedDataPresenter(IFeedDataSource feedDataSource) {
        return new FeedDataPresenter(feedDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public LogInPresenter provideLogInPresenter(ILogInDataSource logInDataSource) {
        return new LogInPresenter(logInDataSource);
    }
}
