package com.moldedbits.android;

import com.moldedbits.android.api.ApiModule;
import com.moldedbits.android.di.ActivityBuilder;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {ApiModule.class, ActivityBuilder.class, AndroidInjectionModule.class})
public interface ApiComponent {

    void inject(BaseApplication application);
}
