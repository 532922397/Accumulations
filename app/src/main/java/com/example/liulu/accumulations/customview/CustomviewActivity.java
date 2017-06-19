package com.example.liulu.accumulations.customview;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;



public class CustomviewActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_customview;
    }

    @OnClick({R.id.btn_flowlayout})
    public void onClick(View view) {

    }

}
