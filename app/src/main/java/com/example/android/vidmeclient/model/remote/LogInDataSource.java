package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.api.VidMeApi;
import com.example.android.vidmeclient.model.entities.AuthResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LogInDataSource implements ILogInDataSource {
    VidMeApi vidMeApi;

    public LogInDataSource(VidMeApi vidMeApi) {
        this.vidMeApi = vidMeApi;
    }

    @Override
    public Observable<AuthResponse> login(String username, String password) {
        return vidMeApi.login(username, password);
    }
}
