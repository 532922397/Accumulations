package com.example.liulu.accumulations.animation;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

public class DanmuActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_danmu2;
    }

    @OnClick({R.id.btn_bili, R.id.btn_xdanmu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bili:
                goToActivity(BiliActivity.class);
                break;
            case R.id.btn_xdanmu:
                goToActivity(XDanmuActivity.class);
                break;
        }
    }
}
