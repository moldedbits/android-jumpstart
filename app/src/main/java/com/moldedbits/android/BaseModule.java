package com.moldedbits.android;

import com.moldedbits.android.api.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * @author shishank
 */
@Module
public class BaseModule {

    private final BaseContracts.BaseView baseView;

    public BaseModule(BaseContracts.BaseView baseView) {
        this.baseView = baseView;
    }

    @Provides
    BasePresenter providesBasePresenter(ApiService apiService) {
        return new BasePresenter(apiService, baseView);
    }
}
