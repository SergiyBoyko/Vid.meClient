package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LogInDataSource implements ILogInDataSource {
    VidMeApi vidMeApi;

    public LogInDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }
}
