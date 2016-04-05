package com.moldedbits.android;

import android.app.Application;

import timber.log.Timber;

/**
 *
 * Created by abhishek on 05/04/16.
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static BaseApplication getInstance() {
        return sInstance;
    }
}
