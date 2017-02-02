package com.moldedbits.android;

import android.app.Application;

import lombok.Getter;
import timber.log.Timber;

/**
 * Created by abhishek on 05/04/16.
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;

    @Getter
    protected APIComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        apiComponent = DaggerAPIComponent.create();
    }

    public static BaseApplication getInstance() {
        return sInstance;
    }
}
