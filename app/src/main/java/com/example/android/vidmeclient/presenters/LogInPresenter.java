package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.ILogInDataSource;
import com.example.android.vidmeclient.model.IUserDataSource;
import com.example.android.vidmeclient.model.entities.AuthResponse;
import com.example.android.vidmeclient.utils.rx.RxErrorAction;
import com.example.android.vidmeclient.utils.rx.RxRetryWithDelay;
import com.example.android.vidmeclient.views.LogInView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LogInPresenter extends BasePresenter<LogInView> {

    private final ILogInDataSource logInDataSource;

    private final IUserDataSource mDataSource;

    public LogInPresenter(ILogInDataSource logInDataSource, IUserDataSource mDataSource) {
        this.logInDataSource = logInDataSource;
        this.mDataSource = mDataSource;
    }

//    public LogInPresenter(ILogInDataSource logInDataSource) {
//        this.logInDataSource = logInDataSource;
//    }

    public void login(String username, String password) {
        addSubscription(logInDataSource.login(username, password)
                .retryWhen(new RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authResponse -> {
                    if (authResponse.getStatus()) {
                        mDataSource.setAuthorized();
                        mDataSource.setToken(authResponse.getAuth().getToken());
                        getView().onLoginSuccess(authResponse);
                    } else getView().onLoginFailed(authResponse.getError());
                }, new RxErrorAction(getView().getContext(), getView()))
        );
    }
}
