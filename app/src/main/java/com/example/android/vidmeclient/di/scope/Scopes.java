package com.example.android.vidmeclient.di.scope;

/**
 * Created by fbrsw on 27.11.2017.
 */

public class Scopes {

    /**
     * Lifecycle scope annotation constants.
     */
    public static final String ACTIVITY = "activity";
    public static final String SERVICE = "service";
    public static final String FRAGMENT = "fragment";
    public static final String VIEW = "view";

    private Scopes() {
        throw new AssertionError("Unable to instantiate");
    }

}
