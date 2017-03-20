package com.example.updatebundle.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by liulu on 2017/1/6.
 */

public class WAGSPUtils {
    private static String key = "liu";

    /**
     * 保持数据
     */
    public static void putString(Context context, String value) {
        SharedPreferences sp = context.getSharedPreferences("liu", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 得到缓存数据
     */
    public static String getString(Context context) {
        SharedPreferences sp = context.getSharedPreferences("liu", Context.MODE_PRIVATE);
        return sp.getString(key, "");

    }

    public static void putBoolean(Context context, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("liu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context) {
        SharedPreferences sp = context.getSharedPreferences("liu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

}
