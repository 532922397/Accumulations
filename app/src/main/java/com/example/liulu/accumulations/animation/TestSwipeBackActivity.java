package com.example.liulu.accumulations.animation;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

public class TestSwipeBackActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_test_swipe_back;
    }

    @Override
    public boolean supportSlideBack() {
        return super.supportSlideBack();
    }

    @Override
    public boolean canBeSlideBack() {
        return super.canBeSlideBack();
    }
}
