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
import android.view.inputmethod.InputMethodManager;
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
import com.example.android.vidmeclient.utils.InternetConnectivityUtil;
import com.example.android.vidmeclient.views.LogInView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

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

        loginButton.setOnClickListener(v -> {
            login();

            // hide keyboard if its possible
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        });

        signUpLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.VID_ME_LINK));
            startActivity(browserIntent);

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
        } else if (!InternetConnectivityUtil.isConnected(this.getContext())) {
            onLoginFailed(getResources().getString(R.string.network_problems));
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
            usernameText.setError(getResources().getString(R.string.minimum_4_sym_message));
            return false;
        }
        if (Character.isDigit(username.charAt(0))) {
            usernameText.setError(getResources().getString(R.string.first_digit_username_message));
            return false;
        } else if (username.contains(" ")) {
            usernameText.setError(getResources().getString(R.string.cannot_contain_space_username_message));
            return false;
        } else {
            usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordText.setError(getResources().getString(R.string.minimum_4_sym_message));
            return false;
        } else if (password.contains(" ")) {
            usernameText.setError(getResources().getString(R.string.cannot_contain_space_pass_message));
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
