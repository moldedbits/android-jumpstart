package com.moldedbits.android

import android.app.Application

import timber.log.Timber

/**
 * Created by abhishek
 * on 05/04/16.
 */
open class BaseApplication : Application() {

    lateinit var apiComponent: ApiComponent
    protected set

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        apiComponent = DaggerApiComponent.create()
    }

    companion object {

        var instance: BaseApplication? = null
            private set
    }
}
