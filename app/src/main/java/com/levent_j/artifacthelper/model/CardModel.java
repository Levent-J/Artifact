package com.levent_j.artifacthelper.model;

import com.google.gson.annotations.SerializedName;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardNameField;
import com.levent_j.artifacthelper.pojo.CardRef;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardModel extends RealmObject {
    @PrimaryKey
    public String cardId;
    public String baseCardId;
    public String cardSet;
    public String cardType;
    public String cardName;
    public String cardText;
    public String miniImgUrl;
    public String largeImgUrl;
    public String inGameImgUrl;
    public String color;
    public RealmList<RefCardModel> refCards;
    public String illustrator;
    public String rarity;
    public String manaCost;
    public String goldCost;
    public String itemType;

    public void transformFromPojo(Card pojo, String setName) {
        cardId = pojo.cardId;
        baseCardId = pojo.baseCardId;
        cardSet = setName;
        cardType = pojo.cardType;
        cardName = pojo.cardName.name;
        cardText = pojo.cardText.name;
        miniImgUrl = pojo.cardMiniImg.name == null?pojo.cardMiniImg.defaultName:pojo.cardMiniImg.name;
        largeImgUrl = pojo.cardLargeImg.name;
        inGameImgUrl = pojo.cardIngameImg.name == null?pojo.cardIngameImg.defaultName:pojo.cardIngameImg.name;
        if (pojo.isBlack) color = "black";
        else if (pojo.isBlue) color = "blue";
        else if (pojo.isGreen) color = "green";
        else if (pojo.isRed) color = "red";
        refCards = new RealmList<>();
        for (CardRef refCard : pojo.refCards) {
            refCards.add(new RefCardModel().transformFromPojo(refCard));
        }
        illustrator = pojo.illustrator;
        rarity = pojo.rarity;
        manaCost = pojo.manaCost;
        goldCost = pojo.goldCost;
        itemType = pojo.itemType;
    }


    @Override
    public String toString() {
        return "CardModel{" +
                "cardId='" + cardId + '\'' +
                ", baseCardId='" + baseCardId + '\'' +
                ", cardSet='" + cardSet + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardText='" + cardText + '\'' +
                ", miniImgUrl='" + miniImgUrl + '\'' +
                ", largeImgUrl='" + largeImgUrl + '\'' +
                ", inGameImgUrl='" + inGameImgUrl + '\'' +
                ", color='" + color + '\'' +
                ", refCards=" + refCards +
                ", illustrator='" + illustrator + '\'' +
                ", rarity='" + rarity + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", goldCost='" + goldCost + '\'' +
                ", itemType='" + itemType + '\'' +
                '}';
    }
}
