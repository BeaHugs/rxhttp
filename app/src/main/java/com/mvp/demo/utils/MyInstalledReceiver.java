package com.mvp.demo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator Wang
 */

public class MyInstalledReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {     // install
            String packageName = intent.getDataString();
            Log.i("homer", "安装了 :" + packageName);
        }

        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   // uninstall
            String packageName = intent.getDataString();
            Log.i("homer", "卸载了 :" + packageName);
        }
    }
}
