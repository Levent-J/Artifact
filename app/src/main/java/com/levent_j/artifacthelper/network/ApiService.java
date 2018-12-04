package com.levent_j.artifacthelper.network;

import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.pojo.CardSetUrlRespone;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public interface ApiService {

//    https://playartifact.com/cardset/01/
    @GET()
    Observable<CardSetUrlRespone> getCardSetUrl(@Url String url);

    @GET()
    Observable<CardSetRespone> getCardSet(@Url String url);

}
