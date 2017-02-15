package com.example.liulu.accumulations.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 通过代码设置json数据
 */
public class ByCodeActivity extends BaseActivity {


    @Bind(R.id.lottieview)
    LottieAnimationView lottieview;
    @Bind(R.id.tv_seek)
    TextView tvSeek;

    @Override
    protected void initData() {
        LottieComposition.fromAssetFileName(this, "LottieLogo1.json", new LottieComposition.OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(LottieComposition composition) { // 成分
                lottieview.setComposition(composition);
                lottieview.setProgress(0.33f);
                lottieview.playAnimation();
            }
        });
        lottieview.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tvSeek.setText("动画进度" + animation.getAnimatedFraction() * 100 + "%");// 分数，部分
            }
        });
        lottieview.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_by_code;
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startAnimation();
                break;
            case R.id.button2:
                stopAnimation();
                break;
        }
    }

    /**
     * 停止动画
     */
    private void stopAnimation() {
        boolean animating = lottieview.isAnimating();
        if (animating) {
            lottieview.cancelAnimation();
        }

    }

    /**
     * 开始动画
     */
    private void startAnimation() {
        boolean animating = lottieview.isAnimating();
        if (!animating) {
            lottieview.setProgress(0);
            lottieview.playAnimation();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieview.cancelAnimation();
    }
}
