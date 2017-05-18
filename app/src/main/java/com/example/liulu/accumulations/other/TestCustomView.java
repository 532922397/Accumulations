package com.example.liulu.accumulations.other;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jh on 2017/2/7
 */

public class TestCustomView extends View {
    public TestCustomView(Context context) {
        super(context);
    }

    public TestCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                Log.e("liulu", "自定view的dispatchTouchEvent的ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("liulu", "自定view的dispatchTouchEvent的ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE :
                Log.e("liulu", "自定view的dispatchTouchEvent的ACTION_MOVE");
                break;
        }


        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                Log.e("liulu", "自定view的onTouchEvent的ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("liulu", "自定view的onTouchEvent的ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE :
                Log.e("liulu", "自定view的onTouchEvent的ACTION_MOVE");
                break;
        }


        return super.onTouchEvent(event);
    }




}
