package com.example.android.vidmeclient.views;

import com.example.android.vidmeclient.model.entities.AuthResponse;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface LogInView extends BaseView {

    void onLoginSuccess(AuthResponse authResponse);
}
