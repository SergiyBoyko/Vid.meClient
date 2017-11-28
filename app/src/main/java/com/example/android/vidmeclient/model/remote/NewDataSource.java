package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewDataSource implements INewDataSource {
    VidMeApi vidMeApi;

    public NewDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }
}
