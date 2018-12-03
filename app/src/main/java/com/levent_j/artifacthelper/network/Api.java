package com.levent_j.artifacthelper.network;

import com.levent_j.artifacthelper.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class Api {

    private static final int HTTP_CONNECT_TIME_OUT = 600;

    private static Api sInstance;
    private ApiService mService;

    public static Api getInstance() {
        if (sInstance == null) {
            synchronized (Api.class) {
                if (sInstance == null) {
                    sInstance = new Api();
                }
            }
        }
        return sInstance;
    }

    private Api() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.CARD_BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mService = retrofit.create(ApiService.class);
    }
}
