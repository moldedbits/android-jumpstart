package com.moldedbits.android;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by abhishek
 * on 05/04/16.
 */
public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    private static BaseApplication instance;

    @Getter
    protected ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

         DaggerApiComponent.create().inject(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
