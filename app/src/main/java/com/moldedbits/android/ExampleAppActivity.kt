package com.moldedbits.android

import android.os.Bundle


class ExampleAppActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_app)
    }
}
