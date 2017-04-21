package com.example.liulu.accumulations.wiget;

import android.util.DisplayMetrics;

import com.example.liulu.accumulations.common.DomeApplication;

/**
 * Created by liulu on 2017/3/24
 */

public class Utils{
    private static DisplayMetrics  mDisplayMetrics ;
    public static int dp2Px(int dp) {
       return  (int)(getDefaultDisplayMetrics().density * dp +0.5f);
    }

    private static DisplayMetrics getDefaultDisplayMetrics() {
        if(mDisplayMetrics == null){
            mDisplayMetrics = DomeApplication.getContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics;
    }
}
