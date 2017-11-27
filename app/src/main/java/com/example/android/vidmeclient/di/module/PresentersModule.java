package com.example.android.vidmeclient.di.module;

import com.example.android.vidmeclient.di.scope.Scope;
import com.example.android.vidmeclient.di.scope.Scopes;
import com.example.android.vidmeclient.module.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.module.remote.INewDataSource;
import com.example.android.vidmeclient.presenters.FeaturedDataPresenter;
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
    public FeaturedDataPresenter provideFeaturedPresenter(IFeaturedDataSource featuredDataSource) {
        return new FeaturedDataPresenter(featuredDataSource);
    }

    @Provides
    @Scope(Scopes.VIEW)
    public NewDataPresenter provideNewPresenter(INewDataSource newDataSource) {
        return new NewDataPresenter(newDataSource);
    }

}
