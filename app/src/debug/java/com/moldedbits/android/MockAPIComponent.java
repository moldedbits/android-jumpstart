package com.moldedbits.android;

import com.moldedbits.android.api.MockAPIModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockAPIModule.class})
interface MockAPIComponent extends APIComponent {
}
