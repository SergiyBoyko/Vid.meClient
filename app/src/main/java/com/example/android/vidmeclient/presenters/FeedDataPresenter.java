package com.example.android.vidmeclient.presenters;

import android.widget.Toast;

import com.example.android.vidmeclient.model.entities.ContentResponse;
import com.example.android.vidmeclient.model.IFeedDataSource;
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

    public void getFeedContent(int lim, int off, String token) {
        addSubscription(feedDataSource.getFeedContent(lim, off, token)
                .retryWhen(new RxRetryWithDelay())
                .map(ContentResponse::getVideos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::showVideoContent, new RxErrorAction(getView().getContext()))
        );
    }

}
