package com.example.android.vidmeclient.views;

import com.example.android.vidmeclient.model.entities.Video;

import java.util.List;

/**
 * Created by fbrsw on 28.11.2017.
 */

public interface BaseContentView extends BaseView {

    void showVideoContent(List<Video> videos);

}
