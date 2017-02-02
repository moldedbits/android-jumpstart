package com.moldedbits.android.api;

/**
 * Created by abhishek on 08/04/16.
 */

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.moldedbits.android.R;
import com.moldedbits.android.models.ApiError;
import com.moldedbits.android.models.response.BaseResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallback<T> implements Callback<BaseResponse<T>> {

    private Activity mActivity;

    public ResponseCallback(Activity activity) {
        mActivity = activity;
    }

    public abstract void onSuccess(Call<BaseResponse<T>> call, BaseResponse<T> response);

    public abstract void onError(ApiError error);

    public abstract void onFailure(Throwable t);

    public abstract void onRetry(Call<BaseResponse<T>> call);


    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        String message;
        ApiError error;

        if (response != null) {
            BaseResponse<T> baseResponse = response.body();
            if (baseResponse != null && baseResponse.getResult() != null) {
                if (response.isSuccessful()) {
                    onSuccess(call, baseResponse);
                } else {
                    error = baseResponse.getError();
                    message = error.getErrors();
                    onError(error);
                    showSnackBar(false, message, call);
                }
                return;
            }
        }

        error = getMockError();
        message = error.getErrors();
        onError(getMockError());

        showSnackBar(false, message, call);
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
        if (t != null) {
            if (t instanceof IOException) {
                showSnackBar(true, mActivity.getString(R.string.internet_slow_or_disconnected),
                        call);
            } else {
                showSnackBar(false, t.getMessage(), call);
            }
            onFailure(t);
        }
    }

    private ApiError getMockError() {
        ApiError apiError = new ApiError();
        apiError.setErrors(mActivity.getString(R.string.something_went_wrong));
        apiError.setSuccess(BaseResponse.Status.FAILURE);
        return apiError;
    }

    private void showSnackBar(boolean isClickable, String message,
                              final Call<BaseResponse<T>> call) {
        Snackbar snackbar = Snackbar.make(mActivity.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_LONG);
        if (isClickable) {
            snackbar.setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (call != null) {
                        onRetry(call);
                    }
                }
            });
        }
        snackbar.show();
    }
}
