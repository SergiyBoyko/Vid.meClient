package com.example.android.vidmeclient.api;

import com.example.android.vidmeclient.model.entities.AuthResponse;
import com.example.android.vidmeclient.model.entities.ContentResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface VidMeApi {
    @GET("/videos/featured")
    Observable<ContentResponse> getFeaturedContent(@Query("limit") int limit,
                                                   @Query("offset") int offset);

    @GET("/videos/new")
    Observable<ContentResponse> getNewContent(@Query("limit") int limit,
                                              @Query("offset") int offset);

    @GET("/videos/feed")
    Observable<ContentResponse> getFeedContent(@Query("limit") int limit,
                                               @Query("offset") int offset,
                                               @Query("token") String token);

    @POST("/auth/create")
    Observable<AuthResponse> login(@Query("username") String username, @Query("password") String password);

    // https://api.vid.me/video/19130214/frepost?token=0c9bc436038c407689fa7f1058cdd5ed
//    @POST("video/{video}/frepost")
//    Observable<AuthResponse> like(@Path("video") String videoId, @Query("token") String token);

}
