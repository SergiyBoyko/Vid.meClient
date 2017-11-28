package com.example.android.vidmeclient.views;

import com.example.android.vidmeclient.model.entities.Video;

import java.util.List;

/**
 * Created by fbrsw on 27.11.2017.
 */

public interface FeaturedContentView extends BaseView {

    void showVideoContents(List<Video> videos);

}
