package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.entities.FeaturedContentResponse;

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
    public Observable<FeaturedContentResponse> getFeaturedContent(int lim, int off) {
        return vidMeApi.getFeaturedContent(lim, off);
    }
}
