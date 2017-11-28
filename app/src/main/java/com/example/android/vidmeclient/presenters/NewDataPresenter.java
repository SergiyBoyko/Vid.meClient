package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.entities.ContentResponse;
import com.example.android.vidmeclient.model.remote.INewDataSource;
import com.example.android.vidmeclient.utils.rx.RxErrorAction;
import com.example.android.vidmeclient.utils.rx.RxRetryWithDelay;
import com.example.android.vidmeclient.views.NewContentView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewDataPresenter extends BasePresenter<NewContentView> {

    private final INewDataSource newDataSource;

    public NewDataPresenter(INewDataSource newDataSource) {
        this.newDataSource = newDataSource;
    }

    public void getNewContent(int lim, int off) {
        subscribe(newDataSource.getNewContent(lim, off)
                .retryWhen(new RxRetryWithDelay())
                .map(ContentResponse::getVideos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::showVideoContent, new RxErrorAction(getView().getContext()))
        );
    }
}
