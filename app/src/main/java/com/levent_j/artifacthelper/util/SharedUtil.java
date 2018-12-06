package com.levent_j.artifacthelper.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.levent_j.artifacthelper.base.GlobalData;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/06.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class SharedUtil {


    private static final String SP_STORE = "test";

    public static void put(String key, Object obj) {
        SharedPreferences sp = GlobalData.app().getSharedPreferences(SP_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else if (obj instanceof String) {
            editor.putString(key, (String) obj);
        }

        editor.apply();
    }

    public static Object get(String key,Object defaultObj){
        SharedPreferences sp = GlobalData.app().getSharedPreferences(SP_STORE, Context.MODE_PRIVATE);

        if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        } else if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        }
        return null;
    }
}
