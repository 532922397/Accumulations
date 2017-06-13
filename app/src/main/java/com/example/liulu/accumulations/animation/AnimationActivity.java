package com.example.liulu.accumulations.animation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {


    @Override
    protected void initData() {
        Toast.makeText(AnimationActivity.this, "我第四次进来了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_animation;
    }

    /*仿饿了么*/
    @OnClick({R.id.btn_shoppingcar1, R.id.btn_shoppingcar2, R.id.btn_transfer, R.id.btn_lottie, R.id.btn_danmu})
    public void goToEShoppingcar(View view) {
        switch (view.getId()) {
            case R.id.btn_shoppingcar1:
                goToActivity(ShoppingcarEActivity.class);
                break;
            case R.id.btn_shoppingcar2:
                goToActivity(ShoppingcarBActivity.class);
                break;
            case R.id.btn_transfer:
                goToActivity(TransferActivity.class);
                break;
            case R.id.btn_lottie:
                goToActivity(LottieActivity.class);
                break;
            case R.id.btn_danmu:
                goToActivity(DanmuActivity.class);
                break;
            case R.id.btn_bg_desktop:
                goToActivity(DesktopBGActivity.class);
                break;
        }

    }

    /*贝塞尔曲线*/
    @OnClick(R.id.btn_shoppingcar2)
    public void goToBShoppingcar(View view) {

    }

    /*沉浸式转场动画*/
    @OnClick(R.id.btn_transfer)
    public void goToTransfer(View view) {

    }

    /*lottie动画*/
    @OnClick(R.id.btn_lottie)

    public void goToLottie(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("liulu", "a+++++++onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("liulu", "a++++++++onDestroy");
    }
}
