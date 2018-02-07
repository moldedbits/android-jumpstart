package bdesir.raneh.salon.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockApiModule {

    @Provides
    @Singleton
    ApiService providesApiService() {
        return new MockApiService();
    }
}
