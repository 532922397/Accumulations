package com.example.liulu.accumulations.wiget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liulu on 2017/3/3
 */

public class TestHotFix {

    public static void show(Context context, String string) {
        Toast.makeText(context.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

}
