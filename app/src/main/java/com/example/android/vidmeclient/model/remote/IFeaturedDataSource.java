package com.example.android.vidmeclient.model.remote;

import com.example.android.vidmeclient.model.entities.FeaturedContentResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface IFeaturedDataSource {

    Observable<FeaturedContentResponse> getFeaturedContent(int lim, int off);

}
