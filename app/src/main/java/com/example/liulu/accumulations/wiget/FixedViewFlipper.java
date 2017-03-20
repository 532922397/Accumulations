package com.example.liulu.accumulations.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created by liulu on 2017/3/20
 */

public class FixedViewFlipper extends ViewFlipper {

    public FixedViewFlipper(Context context) {
        super(context);
    }

    public FixedViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
