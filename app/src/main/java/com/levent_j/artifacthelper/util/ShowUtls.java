package com.levent_j.artifacthelper.util;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/12.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class ShowUtls {
    public static String getRarityText(String rarity){
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
}
