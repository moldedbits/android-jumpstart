package com.moldedbits.android.utils;

import android.app.Activity;
import android.support.v4.content.Loader;

import com.moldedbits.android.api.ResponseCallback;
import com.moldedbits.android.models.ApiError;
import com.moldedbits.android.models.response.BaseResponse;

import lombok.Getter;
import retrofit2.Call;


public abstract class LoaderHandler<T> extends Loader<T> {

    Activity mActivity;

    @Getter
    ResponseCallback<T> callback;

    T data;

    public abstract T getData();

    public LoaderHandler(Activity activity) {
        super(activity);
        mActivity = activity;
        callback = new ResponseCallback<T>(mActivity) {
            @Override
            public void onSuccess(Call<BaseResponse<T>> call, BaseResponse<T> response) {
                data = response.getResult();
            }

            @Override
            public void onError(ApiError error) {
            }

            @Override
            public void onFailure(Throwable t) {
            }

            @Override
            public void onRetry(Call<BaseResponse<T>> call) {
            }
        };
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        data = getData();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (data != null) {
            deliverResult(data);
        } else {
            data = getData();
        }
    }
}
