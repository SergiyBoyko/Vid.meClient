package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.INewDataSource;
import com.example.android.vidmeclient.model.entities.ContentResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewRemoteDataSource implements INewDataSource {
    VidMeApi vidMeApi;

    public NewRemoteDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }

    @Override
    public Observable<ContentResponse> getNewContent(int lim, int off) {
        return vidMeApi.getNewContent(lim, off);
    }
}
