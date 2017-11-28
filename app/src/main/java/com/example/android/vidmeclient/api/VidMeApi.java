package com.example.android.vidmeclient.api;

import com.example.android.vidmeclient.model.entities.FeaturedContentResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface VidMeApi {

    @GET("/videos/featured")
    Observable<FeaturedContentResponse> getFeaturedContent(@Query("limit") int limit, @Query("offset") int offset);

}
