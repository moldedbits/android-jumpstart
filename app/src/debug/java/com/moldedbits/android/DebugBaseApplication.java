package com.moldedbits.android;

public class DebugBaseApplication extends BaseApplication {

    public void enableMockMode() {
        apiComponent = DaggerMockApiComponent.create();
    }
}
