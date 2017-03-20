package com.example.liulu.accumulations.wiget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liulu on 2017/3/3
 */

public class TestHotFix {

    private void privateShow(Context context, String string) {
        Toast.makeText(context.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    public void show1(Context context, String string) {
        Toast.makeText(context.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    public static void show2(Context context, String string) {
        Toast.makeText(context.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    /*非静态*/
    public boolean test1(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        return true;
    }

    /*静态*/
    public static void test2(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public void show(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
