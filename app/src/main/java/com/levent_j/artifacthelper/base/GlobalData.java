package com.levent_j.artifacthelper.base;

public class GlobalData {

    public static final String UMENG_APP_KEY = "5b5007398f4a9d37f800007d";
    public static final String UMENG_APP_SECRET = "7640ccb5f6602e598c70279dda83cf78";
    public static final String UMENG_CHANNEL = "Umeng";


    private static App sContext;

    public static void initialize(App context) {
        sContext = context;
    }

    public static App app() {
        return sContext;
    }

}