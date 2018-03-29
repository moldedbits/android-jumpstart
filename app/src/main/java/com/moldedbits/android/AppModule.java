package com.moldedbits.android;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author shishank
 */
@Module
public class AppModule {

    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return application;
    }
}
