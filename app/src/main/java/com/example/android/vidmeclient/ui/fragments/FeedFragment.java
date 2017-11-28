package com.example.android.vidmeclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.views.FeedView;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class FeedFragment extends Fragment implements FeedView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.featured_fragment, container, false);

        return v;
    }
}
