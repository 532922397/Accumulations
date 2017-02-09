package com.example.liulu.accumulations.other;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;



public class TestCustomViewGroup extends ViewGroup {
    public TestCustomViewGroup(Context context) {
        super(context);
    }

    public TestCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
         switch (event.getAction()) {
             case MotionEvent.ACTION_DOWN :
                 Log.e("liulu", "自定viewgroup的dispatchTouchEvent的ACTION_DOWN");
                 break;
             case MotionEvent.ACTION_UP:
                 Log.e("liulu", "自定viewgroup的dispatchTouchEvent的ACTION_UP");
                 break;
             case MotionEvent.ACTION_MOVE :
                 Log.e("liulu", "自定viewgroup的dispatchTouchEvent的ACTION_MOVE");
                 break;
         } 


        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                Log.e("liulu", "自定viewgroup的onTouchEvent的ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("liulu", "自定viewgroup的onTouchEvent的ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE :
                Log.e("liulu", "自定viewgroup的onTouchEvent的ACTION_MOVE");
                break;
        }


        return super.onTouchEvent(event);
    }


}
