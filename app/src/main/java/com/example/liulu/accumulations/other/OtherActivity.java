package com.example.liulu.accumulations.other;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;


public class OtherActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_other;
    }

    @OnClick(R.id.btn_xml)
    public void XML(View view) {
    goToActivity(XMLActivity.class);
    }
    @OnClick(R.id.btn_test_touch)
    public void TestTouch(View view) {
        goToActivity(TouchActivity.class);
    }
}
