package com.gzd.example.testapplication;

import android.app.Application;

/**
 * Created by gzd on 2019/2/18 0018
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
