package com.moldedbits.android;

import com.moldedbits.android.api.APIModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIModule.class})
public interface APIComponent {

    void inject(BaseActivity baseActivity);
}
