package com.moldedbits.android.api

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockApiModule {

    @Provides
    @Singleton
    internal fun providesApiService(): ApiService {
        return MockApiService()
    }
}
