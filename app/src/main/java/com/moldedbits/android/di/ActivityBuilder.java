package com.moldedbits.android.di;

import com.moldedbits.android.ExampleAppActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder  {

    @ContributesAndroidInjector(modules = ExampleModule.class)
    abstract ExampleAppActivity contributeYourActivityInjector();
}
