package com.example.liulu.accumulations.Test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.liulu.accumulations.R;


/**
 * 开启1px像素的activity测试保活
 */
public class SinglePixelActivity extends AppCompatActivity {

    public static final String TAG = SinglePixelActivity.class.getSimpleName();

    public static void actionToSinglePixelActivity(Context pContext) {
        Intent intent = new Intent(pContext, SinglePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pixel);
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高设计为1个像素
        attributes.width = 1;
        attributes.height = 1;
        //起始坐标
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        Log.e("liulu", "SinglePixelActivity  onCreate");
        ScreenManager.getInstance(this).setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("liulu", "SinglePixelActivity  onDestroy");
    }
}
