package com.moldedbits.android;

import android.os.Bundle;

import com.moldedbits.android.example.ExamplePresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class ExampleAppActivity extends BaseDrawerActivity {

    @Inject
    ExamplePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_app);

        presenter.doSomething();
    }
}
