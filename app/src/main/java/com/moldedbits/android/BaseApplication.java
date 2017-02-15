package com.moldedbits.android;

import android.app.Application;

import lombok.Getter;
import timber.log.Timber;

/**
 * Created by abhishek
 * on 05/04/16.
 */
public class BaseApplication extends Application {

    privatesd static BaseApplication instance;

    @Getter
    protected ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        apiComponent = DaggerApiComponent.create();
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
