package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.remote.IFeaturedDataSource;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeaturedDataPresenter {

    private final IFeaturedDataSource featuredDataSource;

    public FeaturedDataPresenter(IFeaturedDataSource featuredDataSource) {
        this.featuredDataSource = featuredDataSource;
    }
}
