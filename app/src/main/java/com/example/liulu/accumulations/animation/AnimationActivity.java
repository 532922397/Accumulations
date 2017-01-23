package com.example.liulu.accumulations.animation;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_animation;
    }

    /*仿饿了么*/
    @OnClick(R.id.btn_shoppingcar1)
    public void goToEShoppingcar(View view) {
        goToActivity(ShoppingcarEActivity.class);
    }

    /*贝塞尔曲线*/
    @OnClick(R.id.btn_shoppingcar2)
    public void goToBShoppingcar(View view) {
        goToActivity(ShoppingcarBActivity.class);
    }

}
