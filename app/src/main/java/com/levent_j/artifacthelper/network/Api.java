package com.levent_j.artifacthelper.network;

import com.levent_j.artifacthelper.BuildConfig;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.pojo.CardSetUrlRespone;
import com.levent_j.artifacthelper.util.Constans;
import com.levent_j.artifacthelper.util.RxUtil;
import com.levent_j.artifacthelper.util.SharedUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
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

    /**
     * 获取缓存的CardSet的URL
     * @param cardSet
     * @return
     */
    private String getCardSetUrlLocal(String cardSet) {
        //先查一下 本地是否缓存了url
        String url = (String) SharedUtil.get(cardSet + Constans.SP_KEY_URL, "");
        if (!url.isEmpty()) {
            long exp = (long) SharedUtil.get(cardSet + Constans.SP_KEY_EXP, "");
            long current = System.currentTimeMillis();
            if (exp > current){//如果url还未过期 则可以重复使用
                return url;
            }
        }
        //重新获取url
        return null;
    }

    /**
     * 获取CardSet
     * @param cardSet
     * @return
     */
    public Observable<CardSetRespone> getCardSet(String cardSet) {
        //先获取url
        String url = getCardSetUrlLocal(cardSet);
        if (url!=null&&!url.isEmpty()){
            return mService
                    .getCardSet(url)
                    .compose(RxUtil.<CardSetRespone>RxSchedulers());
        }else {
            return
                    mService
                    .getCardSetUrl(Constans.CARD_SET_URL+cardSet)
                    .flatMap(new Function<CardSetUrlRespone, Observable<CardSetRespone>>() {
                        @Override
                        public Observable<CardSetRespone> apply(CardSetUrlRespone cardSetUrlRespone) throws Exception {
                            String url = cardSetUrlRespone.getCdnRoot() + cardSetUrlRespone.getUrl();
                            long exp = cardSetUrlRespone.getExpireTime();
                            SharedUtil.put(cardSet+Constans.SP_KEY_URL,url);
                            SharedUtil.put(cardSet+Constans.SP_KEY_EXP,exp);
                            return mService.getCardSet(url);
                        }
                    })
                    .compose(RxUtil.<CardSetRespone>RxSchedulers());
        }

    }


}
