package com.moldedbits.android;

import com.moldedbits.android.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent {
    void inject(BaseApplication baseApplication);

    BaseComponent getBaseComponent(BaseModule module);
}
