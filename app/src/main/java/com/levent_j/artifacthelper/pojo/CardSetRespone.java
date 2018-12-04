package com.levent_j.artifacthelper.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/04.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardSetRespone {

    @SerializedName("card_set")
    public CardSet cardSet;

    public static class CardSet{

        @SerializedName("version")
        public int version;

        @SerializedName("set_info")
        public CardSetInfo cardSetInfo;

        @SerializedName("card_list")
        public List<Card> cardList;

        @Override
        public String toString() {
            return "CardSet{" +
                    "version=" + version +
                    ", cardSetInfo=" + cardSetInfo +
                    ", cardList=" + cardList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CardSetRespone{" +
                "cardSet=" + cardSet +
                '}';
    }
}
