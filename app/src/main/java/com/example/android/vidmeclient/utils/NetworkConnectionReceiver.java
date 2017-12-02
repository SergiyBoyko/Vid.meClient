package com.example.android.vidmeclient.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by fbrsw on 28.11.2017.
 */

public abstract class NetworkConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!isInitialStickyBroadcast()) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnected() || mobile.isConnected()) {
                action();
            } else {
                Toast.makeText(context, "Problem with Network Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public abstract void action();

}
