package com.levent_j.artifacthelper.util;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/12.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class ShowUtls {
    public static String getRarityText(String rarity){
        if (rarity==null){
            rarity = "";
        }
        switch (rarity){
            case "Uncommon":
                return "非普通";
            case "Rare":
                return "稀有";
            case "Common":
                return "普通";
            default:
                return "基本";
        }
    }

    public static String getHtmlText(String text){
        if (text == null){
            text = "";
        }
        return text;
    }

    public static String getCardType(String type){
        if (type == null){
            type = "";
        }
        switch (type){
            case "Creep":
                return "小兵";
            case "Hero":
                return "英雄";
            case "Improvement":
                return "强化";
            case "Item":
                return "物品";
            default:
                return "法术";
        }
    }

    public static String getColor(String color){
        switch (color){
            case "red":
                return "红色";
            case "blue":
                return "蓝色";
            case "black":
                return "黑色";
            default:
                return "绿色";
        }
    }
}
