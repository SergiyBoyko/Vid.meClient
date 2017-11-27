package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.module.remote.IFeedDataSource;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeedDataPresenter {

    private final IFeedDataSource feedDataSource;

    public FeedDataPresenter(IFeedDataSource feedDataSource) {
        this.feedDataSource = feedDataSource;
    }
}
