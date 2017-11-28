package com.example.android.vidmeclient.utils.rx;

import android.content.Context;
import android.widget.Toast;

import com.example.android.vidmeclient.R;

import rx.functions.Action1;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class RxErrorAction implements Action1<Throwable> {

    private final Context context;

    public RxErrorAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        System.out.println("13579234567891231232412416");
        throwable.printStackTrace();

        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show();
    }

}
