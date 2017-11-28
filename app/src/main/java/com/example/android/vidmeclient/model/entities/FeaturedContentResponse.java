package com.example.android.vidmeclient.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class FeaturedContentResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
//    @SerializedName("watching")
//    @Expose
//    private Watching watching;
    @SerializedName("viewerVotes")
    @Expose
    private List<Object> viewerVotes = null;
    @SerializedName("viewerReposts")
    @Expose
    private Object viewerReposts;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

//    public Watching getWatching() {
//        return watching;
//    }
//
//    public void setWatching(Watching watching) {
//        this.watching = watching;
//    }

    public List<Object> getViewerVotes() {
        return viewerVotes;
    }

    public void setViewerVotes(List<Object> viewerVotes) {
        this.viewerVotes = viewerVotes;
    }

    public Object getViewerReposts() {
        return viewerReposts;
    }

    public void setViewerReposts(Object viewerReposts) {
        this.viewerReposts = viewerReposts;
    }
}
