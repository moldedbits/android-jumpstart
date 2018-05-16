package com.moldedbits.android

import com.moldedbits.android.api.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun inject(baseActivity: BaseActivity)
}
