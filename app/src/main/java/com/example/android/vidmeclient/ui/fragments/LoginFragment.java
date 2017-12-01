package com.example.android.vidmeclient.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vidmeclient.AppVidMe;
import com.example.android.vidmeclient.common.Constants;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.di.component.DaggerPresentersComponent;
import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.model.entities.AuthResponse;
import com.example.android.vidmeclient.presenters.LogInPresenter;
import com.example.android.vidmeclient.ui.activities.LaunchActivity;
import com.example.android.vidmeclient.views.LogInView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class LoginFragment extends Fragment implements LogInView {

    @BindView(R.id.input_username)
    EditText usernameText;
    @BindView(R.id.input_password)
    EditText passwordText;
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.link_signup)
    TextView signUpLink;

    @Inject
    LogInPresenter presenter;

    private ProgressDialog progressDialog;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.VID_ME_LINK));
                startActivity(browserIntent);


            }
        });


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.authenticating));

        presenter.setView(this);
        return view;
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppVidMe getApp() {
        return (AppVidMe) getActivity().getApplication();
    }

    private void login() {

        if (!validate()) {
            onLoginFailed(getResources().getString(R.string.incorrect_credentials));
            return;
        }

        loginButton.setEnabled(false);

        progressDialog.show();

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        presenter.login(username, password);
    }

    @Override
    public void onLoginSuccess(AuthResponse authResponse) {
        loginButton.setEnabled(true);
        progressDialog.hide();
        if (authResponse.getStatus()) {
            startFeedFragment();
        } else showText(authResponse.getError());

    }

    @Override
    public void onLoginFailed(String message) {
        showText(message);
        loginButton.setEnabled(true);
        progressDialog.hide();
    }

    private void startFeedFragment() {
        ((LaunchActivity) getActivity()).showLogOutMenuItem(true);

        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.root_frame, new FeedFragment());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
    }

    public boolean validate() {

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if (username.isEmpty() || username.length() < 4) {
            usernameText.setError("minimum 4 symbols");
            return false;
        }
        if (Character.isDigit(username.charAt(0))) {
            usernameText.setError("username cannot begin with digit");
            return false;
        } else if (username.contains(" ")) {
            usernameText.setError("username cannot contain space");
            return false;
        } else {
            usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordText.setError("minimum 4 symbols");
            return false;
        } else if (password.contains(" ")) {
            usernameText.setError("password cannot contain space");
            return false;
        } else {
            passwordText.setError(null);
        }

        return true;
    }

    private void showText(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
