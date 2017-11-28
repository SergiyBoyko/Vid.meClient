package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.entities.ContentResponse;
import com.example.android.vidmeclient.model.remote.IFeaturedDataSource;
import com.example.android.vidmeclient.utils.rx.RxErrorAction;
import com.example.android.vidmeclient.utils.rx.RxRetryWithDelay;
import com.example.android.vidmeclient.views.FeaturedContentView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class FeaturedDataPresenter extends BasePresenter<FeaturedContentView> {

    private final IFeaturedDataSource featuredDataSource;

    public FeaturedDataPresenter(IFeaturedDataSource featuredDataSource) {
        this.featuredDataSource = featuredDataSource;
    }

    public void getFeaturedContent(int lim, int off) {
        subscribe(featuredDataSource.getFeaturedContent(lim, off)
                .retryWhen(new RxRetryWithDelay())
                .map(ContentResponse::getVideos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::showVideoContent, new RxErrorAction(getView().getContext()))
        );
    }
}
