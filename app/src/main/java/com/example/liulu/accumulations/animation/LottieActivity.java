package com.example.liulu.accumulations.animation;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class LottieActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_lottie;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                goToActivity(SimpleActivity.class);
                break;
            case R.id.button2:
                goToActivity(ByCodeActivity.class);
                break;
            case R.id.button3:
                goToActivity(ByNetActivity.class);
                break;
        }
    }


}
