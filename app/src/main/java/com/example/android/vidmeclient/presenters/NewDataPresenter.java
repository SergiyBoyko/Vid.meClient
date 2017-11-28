package com.example.android.vidmeclient.presenters;

import com.example.android.vidmeclient.model.remote.INewDataSource;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewDataPresenter {

    private final INewDataSource newDataSource;

    public NewDataPresenter(INewDataSource newDataSource) {
        this.newDataSource = newDataSource;
    }
}
