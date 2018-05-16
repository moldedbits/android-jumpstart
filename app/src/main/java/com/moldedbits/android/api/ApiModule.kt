package com.moldedbits.android.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.moldedbits.android.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by abhishek
 * on 05/04/16.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesApiService(): ApiService {
        val builder = OkHttpClient.Builder()
        // TODO: 08/04/16 might want to remove this in prod
        builder.hostnameVerifier { str, sslSession -> true }

        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        // Add headers
        builder.interceptors().add(Interceptor { chain ->
            var request = chain.request()

            request = request.newBuilder()
                    // // TODO: 08/04/16 add project specific stuff here
                    .addHeader("Authorization", "Basic YWRtaW46ZG90c2xhc2g=")
                    .build()
            chain.proceed(request)
        })

        // Logging
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(interceptor)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd")
                .create()

        val rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io())

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build()

        return retrofit.create(ApiService::class.java)
    }
}