package com.example.liulu.accumulations.rxjava;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class RxJavaMainActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_rx_java_main;
    }

    @OnClick({R.id.btn_scheduler,R.id.btn_operators1, R.id.btn_operators2, R.id.btn_operators3, R.id.btn_operators4, R.id.activity_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scheduler:
                goToActivity(SchedulerActivity.class);
                break;
            case R.id.btn_operators1:
                goToActivity(Operators1Activity.class);
                break;
            case R.id.btn_operators2:
                goToActivity(Operators2Activity.class);
                break;
            case R.id.btn_operators3:
                goToActivity(PollingActivity.class);
                break;
            case R.id.btn_operators4:
                goToActivity(DebounceEditTextActivity.class);
                break;

        }
    }
}
