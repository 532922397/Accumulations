package com.example.liulu.accumulations.rxjava;

import android.support.v4.app.FragmentTransaction;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.wiget.Log;
import com.example.liulu.accumulations.wiget.LogFragment;
import com.example.liulu.accumulations.wiget.LogWrapper;
import com.example.liulu.accumulations.wiget.MessageOnlyLogFilter;

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

    @Override
    public void initializeLogging() {
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.e("liulu", "Ready");
    }
}
