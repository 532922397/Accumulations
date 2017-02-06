package com.example.liulu.accumulations.animation;

import android.animation.Animator;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;

public class TransferSecondActivity extends BaseActivity {


    @Bind(R.id.activity_transfer_second)
    RelativeLayout activityTransferSecond;

    @Override
    protected void initData() {
        // 状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 导航栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        activityTransferSecond.post(new Runnable() {
            @Override
            public void run() {
                // 圆形动画的坐标为View的中心
                int x = activityTransferSecond.getRight() / 2;
                int y = activityTransferSecond.getBottom() / 2;
                float startRadius = 0f;
                float endRadius = (float) Math.sqrt(x * x + y * y);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(activityTransferSecond, x, y, startRadius, endRadius);
                    animator.setInterpolator(new AccelerateInterpolator());// 加速
                    animator.setDuration(600);
                    animator.start();

                }
            }
        });


    }

    @Override
    public int getLayout() {
        return R.layout.activity_transfer_second;
    }

}
