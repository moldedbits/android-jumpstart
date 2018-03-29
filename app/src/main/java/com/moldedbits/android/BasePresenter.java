package com.moldedbits.android;

import com.moldedbits.android.api.ApiService;

/**
 * @author shishank
 */
public class BasePresenter {

    private ApiService apiService;

    public BasePresenter(ApiService apiService, BaseContracts.BaseView baseView) {
        this.apiService = apiService;
    }
}
