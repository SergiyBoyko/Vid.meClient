package com.example.android.vidmeclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.vidmeclient.AppVidMe;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerPresentersComponent;
import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.model.IUserDataSource;

import javax.inject.Inject;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class RootFragment extends Fragment {

    @Inject
    IUserDataSource mUserDataSource;

    public static RootFragment newInstance() {
        return new RootFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment, container, false);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        notifyUpdates();
        return view;
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppVidMe getApp() {
        return (AppVidMe) getActivity().getApplication();
    }

    public void notifyUpdates() {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        if (!mUserDataSource.isAuthorized())
            transaction.replace(R.id.root_frame, LoginFragment.newInstance());
        else transaction.replace(R.id.root_frame, FeedFragment.newInstance());

        transaction.commit();
    }
}
