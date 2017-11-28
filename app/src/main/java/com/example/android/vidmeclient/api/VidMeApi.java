package com.example.android.vidmeclient.api;

import com.example.android.vidmeclient.model.entities.ContentResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface VidMeApi {

    @GET("/videos/featured")
    Observable<ContentResponse> getFeaturedContent(@Query("limit") int limit, @Query("offset") int offset);

    @GET("/videos/new")
    Observable<ContentResponse> getNewContent(@Query("limit") int limit, @Query("offset") int offset);
    
    @GET("/videos/feed")
    Observable<ContentResponse> getFeedContent(@Query("limit") int limit, @Query("offset") int offset);

}
