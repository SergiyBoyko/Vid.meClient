package com.example.android.vidmeclient.model;

import com.example.android.vidmeclient.model.entities.ContentResponse;

import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface INewDataSource {

    Observable<ContentResponse> getNewContent(int lim, int off);

}
