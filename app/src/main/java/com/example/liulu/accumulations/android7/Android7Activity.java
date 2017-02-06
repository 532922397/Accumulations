package com.example.liulu.accumulations.android7;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class Android7Activity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_android7;
    }


    @OnClick(R.id.btn_notification)
    public void goToNotification(View view) {
        goToActivity(NotificationActivity.class);
    }
}
