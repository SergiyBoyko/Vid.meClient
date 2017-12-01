package com.example.android.vidmeclient.di.component;

import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.di.scope.Scope;
import com.example.android.vidmeclient.di.scope.Scopes;
import com.example.android.vidmeclient.ui.activities.LaunchActivity;
import com.example.android.vidmeclient.ui.fragments.FeaturedFragment;
import com.example.android.vidmeclient.ui.fragments.FeedFragment;
import com.example.android.vidmeclient.ui.fragments.LoginFragment;
import com.example.android.vidmeclient.ui.fragments.NewFragment;
import com.example.android.vidmeclient.ui.fragments.RootFragment;

import dagger.Component;

/**
 * Created by fbrsw on 27.11.2017.
 */

@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {

    void inject(FeaturedFragment featuredFragment);

    void inject(NewFragment newFragment);

    void inject(FeedFragment feedFragment);

    void inject(LoginFragment loginFragment);

    void inject(LaunchActivity launchActivity);

    void inject(RootFragment rootFragment);

}
