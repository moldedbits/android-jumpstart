package com.moldedbits.android.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockAPIModule {

    @Provides
    @Singleton
    APIService providesAPIService() {
        return new MockAPIService();
    }
}
