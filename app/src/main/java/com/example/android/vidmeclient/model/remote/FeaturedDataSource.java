package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.entities.ContentResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeaturedDataSource implements IFeaturedDataSource {
    VidMeApi vidMeApi;

    public FeaturedDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }

    @Override
    public Observable<ContentResponse> getFeaturedContent(int lim, int off) {
        return vidMeApi.getFeaturedContent(lim, off);
    }
}
