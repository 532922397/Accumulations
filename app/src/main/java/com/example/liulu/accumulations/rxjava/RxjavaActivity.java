package com.example.liulu.accumulations.rxjava;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class RxjavaActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_rxjava;
    }

    @OnClick({R.id.btn_rxjava, R.id.btn_retrofit, R.id.btn_rxandroid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava:
                goToActivity(RxJavaMainActivity.class);
                break;
            case R.id.btn_retrofit:
                goToActivity(RxJavaRetrofitActivity.class);
                break;
            case R.id.btn_rxandroid:
                goToActivity(RxAndroidActivity.class);
                break;
        }

    }
}
