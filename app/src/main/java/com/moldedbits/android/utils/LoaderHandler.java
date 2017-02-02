package com.moldedbits.android.utils;

import android.app.Activity;
import android.support.v4.content.Loader;

import com.moldedbits.android.api.ResponseCallback;
import com.moldedbits.android.models.ApiError;
import com.moldedbits.android.models.response.BaseResponse;

import lombok.Getter;
import retrofit2.Call;


public abstract class LoaderHandler<T> extends Loader<T> {

    private final Activity activity;

    @Getter
    ResponseCallback<T> callback;

    private T data;

    public abstract T getData();

    protected LoaderHandler(Activity activity) {
        super(activity);
        this.activity = activity;
        callback = new ResponseCallback<T>(LoaderHandler.this.activity) {
            @Override
            public void onSuccess(Call<BaseResponse<T>> call, BaseResponse<T> response) {
                data = response.getResult();
            }

            @Override
            public void onError(ApiError error) {
            }

            @Override
            public void onFailure(Throwable throwable) {
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
