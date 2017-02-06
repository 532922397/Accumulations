package com.example.liulu.accumulations.animation;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class TansferActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_tansfer;
    }

    @OnClick(R.id.btn_go)
    public void goSecond(View view) {
        goToActivity(TransferSecondActivity.class);
    }


}
