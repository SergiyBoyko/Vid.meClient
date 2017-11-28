package com.example.android.vidmeclient.di.component;

import com.example.android.vidmeclient.di.module.PresentersModule;
import com.example.android.vidmeclient.di.scope.Scope;
import com.example.android.vidmeclient.di.scope.Scopes;
import com.example.android.vidmeclient.ui.fragments.FeaturedFragment;

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

}
