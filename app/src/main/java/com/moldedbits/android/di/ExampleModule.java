package com.moldedbits.android.di;

import com.moldedbits.android.example.ExamplePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ExampleModule {

    @Provides
    ExamplePresenter getPresenter() {
        return new ExamplePresenter();
    }
}
