package com.example.liulu.accumulations.animation;

import android.view.View;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.view.NumberRunningTextView;

import butterknife.Bind;
import butterknife.OnClick;


public class NumberRunningActivity extends BaseActivity {

    @Bind(R.id.tv_money)
    NumberRunningTextView tvMoney;
    @Bind(R.id.tv_num)
    NumberRunningTextView tvNum;
    @Bind(R.id.start)
    TextView start;
    @Bind(R.id.get)
    TextView get;


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_number_running;
    }

    @OnClick({R.id.start, R.id.get})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                tvMoney.setContent("4599.00");
                tvNum.setContent("5000");
                break;
            case R.id.get:
                tvMoney.setContent("0.00");
                tvNum.setContent("0");
                break;
        }
    }
}
