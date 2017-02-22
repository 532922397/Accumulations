package com.example.liulu.accumulations;

import android.view.View;

import com.example.liulu.accumulations.android5.FiveActivity;
import com.example.liulu.accumulations.android7.Android7Activity;
import com.example.liulu.accumulations.animation.AnimationActivity;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.customview.CustomviewActivity;
import com.example.liulu.accumulations.dagger2.Dagger2Activity;
import com.example.liulu.accumulations.integeration.IntegerationActivity;
import com.example.liulu.accumulations.other.OtherActivity;
import com.example.liulu.accumulations.rxjava.RxjavaActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_animation, R.id.btn_five, R.id.btn_customview, R.id.btn_intgeration, R.id.btn_intent, R.id.btn_other, R.id.btn_seven, R.id.btn_rxjava,R.id.btn_dagger2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_animation:
                goToActivity(AnimationActivity.class);
                break;
            case R.id.btn_five:
                goToActivity(FiveActivity.class);
                break;
            case R.id.btn_customview:
                goToActivity(CustomviewActivity.class);
                break;
            case R.id.btn_intgeration:
                goToActivity(IntegerationActivity.class);
                break;
            case R.id.btn_intent:
                goToActivity(IntentActivity.class);
                break;
            case R.id.btn_other:
                goToActivity(OtherActivity.class);
                break;
            case R.id.btn_seven:
                goToActivity(Android7Activity.class);
                break;
            case R.id.btn_rxjava:
                goToActivity(RxjavaActivity.class);
                break;
            case R.id.btn_dagger2:
                goToActivity(Dagger2Activity.class);
                break;
        }
    }
}
