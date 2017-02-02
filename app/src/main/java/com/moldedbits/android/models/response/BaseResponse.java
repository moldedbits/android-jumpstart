package com.moldedbits.android.models.response;

import com.google.gson.annotations.SerializedName;
import com.moldedbits.android.models.ApiError;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by abhishek
 * on 08/04/16.
 */
public class BaseResponse<T> {

    @Getter
    @Setter
    Status status;

    @Getter
    @Setter
    ApiError error;

    @Getter
    @Setter
    T result;

    public enum Status {
        @SerializedName("success")
        SUCCESS("success"),

        @SerializedName("failure")
        FAILURE("failure");

        @Getter
        String value;

        Status(String value) {
            this.value = value;
        }
    }
}
