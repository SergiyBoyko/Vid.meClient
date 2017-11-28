package com.example.android.vidmeclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.views.NewContentView;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class NewFragment extends Fragment implements NewContentView {

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
