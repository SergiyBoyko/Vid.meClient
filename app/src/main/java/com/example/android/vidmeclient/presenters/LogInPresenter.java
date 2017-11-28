package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.remote.ILogInDataSource;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LogInPresenter {

    private final ILogInDataSource logInDataSource;

    public LogInPresenter(ILogInDataSource logInDataSource) {
        this.logInDataSource = logInDataSource;
    }
}
