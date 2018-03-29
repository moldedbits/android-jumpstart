package com.moldedbits.android;

/**
 * @author shishank
 */
public interface BaseContracts {

    interface BaseView {

        void initView();

        void showLoading();

        void hideLoading();
    }

    interface BasePresenter {
        void init();

        void showLoading();

        void hideLoading();
    }
}
