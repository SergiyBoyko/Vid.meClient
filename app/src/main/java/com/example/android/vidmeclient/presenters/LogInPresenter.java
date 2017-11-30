package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.remote.ILogInDataSource;
import com.example.android.vidmeclient.utils.rx.RxErrorAction;
import com.example.android.vidmeclient.utils.rx.RxRetryWithDelay;
import com.example.android.vidmeclient.views.LogInView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LogInPresenter extends BasePresenter<LogInView> {

    private final ILogInDataSource logInDataSource;

    public LogInPresenter(ILogInDataSource logInDataSource) {
        this.logInDataSource = logInDataSource;
    }

    public void login(String username, String password) {
        subscribe(logInDataSource.login(username, password)
                .retryWhen(new RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::onLoginSuccess, new RxErrorAction(getView().getContext()))
        );
    }
}
