package com.moldedbits.android;

import dagger.Subcomponent;

/**
 * @author shishank
 */
@Subcomponent(modules = {BaseModule.class})
public interface BaseComponent {
    void inject(BaseActivity activity);
}
