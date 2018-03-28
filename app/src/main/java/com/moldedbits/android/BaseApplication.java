package com.moldedbits.android;

import android.app.Application;

import lombok.Getter;
import timber.log.Timber;

/**
 * Created by abhishek
 * on 05/04/16.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Getter
    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
