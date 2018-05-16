package com.moldedbits.android

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout

abstract class BaseToolBarActivity : BaseActivity() {

    private var contentFrame: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base_toolbar)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        contentFrame = findViewById(R.id.activity_content)
    }

    override fun setContentView(layoutResId: Int) {
        layoutInflater.inflate(layoutResId, contentFrame, true)
    }
}
