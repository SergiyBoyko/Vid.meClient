package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.entities.ContentResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeedDataSource implements IFeedDataSource {
    VidMeApi vidMeApi;

    public FeedDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }

    @Override
    public Observable<ContentResponse> getFeedContent(int lim, int off, String token) {
        return vidMeApi.getFeedContent(lim, off, token);
    }
}
