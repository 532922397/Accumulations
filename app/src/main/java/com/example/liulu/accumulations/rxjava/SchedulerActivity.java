package com.example.liulu.accumulations.rxjava;

import android.support.v4.app.FragmentTransaction;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

public class SchedulerActivity extends BaseActivity {


    @Override
    protected void initData() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SchedulerFragment schedulerFragment = new SchedulerFragment();
        transaction.replace(R.id.sample_content_fragment, schedulerFragment);
        transaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_scheduler;
    }
}
