package com.example.android.vidmeclient.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by fbrsw on 27.11.2017.
 */

@javax.inject.Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {

    String value() default "";

}
