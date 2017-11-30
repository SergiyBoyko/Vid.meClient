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

/**
 * Created by fbrsw on 28.11.2017.
 */

public class RootFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment, container, false);
        notifyUpdates();
        return view;
    }

    public void notifyUpdates() {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        if (((AppVidMe) getActivity().getApplication()).getUserProfile() == null)
            transaction.replace(R.id.root_frame, new LoginFragment());
        else transaction.replace(R.id.root_frame, new FeedFragment());

        transaction.commit();
    }
}
