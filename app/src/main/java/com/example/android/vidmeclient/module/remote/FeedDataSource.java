package com.example.android.vidmeclient.module.remote;

import com.example.android.vidmeclient.api.VidMeApi;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeedDataSource implements IFeedDataSource {
    VidMeApi vidMeApi;

    public FeedDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }
}
