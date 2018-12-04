package com.levent_j.artifacthelper.util;

import com.orhanobut.logger.Logger;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MyLog {
    public static void d(String msg){
        Logger.d(msg);
    }

    public static void d(Object o){
        Logger.d(o);
    }

    public static void e(String msg){
        Logger.e(msg);
    }
}
