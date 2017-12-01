package com.example.android.vidmeclient.utils.rx;

import android.content.Context;
import android.widget.Toast;

import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.model.entities.AuthResponse;
import com.example.android.vidmeclient.views.BaseView;
import com.example.android.vidmeclient.views.LogInView;
import com.google.gson.Gson;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * Created by fbrsw on 28.11.2017.
 */

public class RxErrorAction implements Action1<Throwable> {

    private final Context context;

    private BaseView view;

    public RxErrorAction(Context context) {
        this.context = context;
    }

    public RxErrorAction(Context context, BaseView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void call(Throwable throwable) {
        System.out.println("13579234567891231232412416"); // TODO
        throwable.printStackTrace();

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try {
                if (httpException.code() / 100 == 4 && view instanceof LogInView) {
                    String json = httpException.response().errorBody().string();
                    Gson gson = new Gson();
                    AuthResponse errorDto = gson.fromJson(json, AuthResponse.class);
                    ((LogInView) view).onLoginFailed(errorDto.getError());
                } else showRequestError();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else showRequestError();
    }

    private void showRequestError() {
        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show();
    }

}
