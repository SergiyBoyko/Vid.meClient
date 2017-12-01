package com.example.android.vidmeclient.ui.fragments;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.android.vidmeclient.utils.NetworkConnectionReceiver;

/**
 * Created by dev_serhii on 29.11.2017.
 */

public abstract class BaseFragment extends Fragment {

    private NetworkConnectionReceiver receiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new NetworkConnectionReceiver() {
            @Override
            public void action() {
                networkConnected();
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    public abstract void networkConnected();
}
