package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.entities.ContentResponse;
import com.example.android.vidmeclient.model.remote.IFeedDataSource;
import com.example.android.vidmeclient.utils.rx.RxErrorAction;
import com.example.android.vidmeclient.utils.rx.RxRetryWithDelay;
import com.example.android.vidmeclient.views.FeedContentView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeedDataPresenter extends BasePresenter<FeedContentView> {

    private final IFeedDataSource feedDataSource;

    public FeedDataPresenter(IFeedDataSource feedDataSource) {
        this.feedDataSource = feedDataSource;
    }

    public void getFeedContent(int lim, int off) {
        subscribe(feedDataSource.getFeedContent(lim, off)
                .retryWhen(new RxRetryWithDelay())
                .map(ContentResponse::getVideos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::showVideoContent, new RxErrorAction(getView().getContext()))
        );
    }

}
