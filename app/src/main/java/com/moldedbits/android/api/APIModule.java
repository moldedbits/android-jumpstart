package com.moldedbits.android.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moldedbits.android.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 *
 * Created by abhishek on 05/04/16.
 */
@Module
public class APIModule {

    @Provides
    @Singleton
    APIService providesAPIService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // TODO: 08/04/16 might want to remove this in prod
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        // Add headers
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                request = request.newBuilder()
                        // // TODO: 08/04/16 add project specific stuff here
                        .addHeader("Authorization", "Basic YWRtaW46ZG90c2xhc2g=")
                        .build();
                return chain.proceed(request);

            }
        });

        // Logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(interceptor);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .create();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
                .createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(APIService.class);
    }
}