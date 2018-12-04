package com.levent_j.artifacthelper.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/04.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class Card {

    @SerializedName("card_id")
    public String cardId;

    @SerializedName("base_card_id")
    public String baseCardId;

    @SerializedName("card_type")
    public String cardType;

    @SerializedName("card_name")
    public CardNameField cardName;

    @SerializedName("card_text")
    public CardNameField cardText;

    @SerializedName("mini_image")
    public CardNameField cardMiniImg;

    @SerializedName("large_image")
    public CardNameField cardLargeImg;

    @SerializedName("ingame_image")
    public CardNameField cardIngameImg;

    @SerializedName("is_green")
    public boolean isGreen;

    @SerializedName("is_blue")
    public boolean isBlue;

    @SerializedName("is_red")
    public boolean isRed;

    @SerializedName("is_black")
    public boolean isBlack;

    @SerializedName("references")
    public List<CardRef> refCards;

    @SerializedName("illustrator")
    public String illustrator;

    @SerializedName("rarity")
    public String rarity;

    @SerializedName("mana_cost")
    public String manaCost;

    @SerializedName("gold_cost")
    public String goldCost;

    @SerializedName("subType")
    public String itemType;

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", baseCardId='" + baseCardId + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardName=" + cardName +
                ", cardText=" + cardText +
                ", cardMiniImg=" + cardMiniImg +
                ", cardLargeImg=" + cardLargeImg +
                ", cardIngameImg=" + cardIngameImg +
                ", isGreen=" + isGreen +
                ", isBlue=" + isBlue +
                ", isRed=" + isRed +
                ", isBlack=" + isBlack +
                ", refCards=" + refCards +
                ", illustrator='" + illustrator + '\'' +
                ", rarity='" + rarity + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", goldCost='" + goldCost + '\'' +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
