package com.moldedbits.android

class DebugBaseApplication : BaseApplication() {

    fun enableMockMode() {
        apiComponent = DaggerMockApiComponent.create()
    }
}
