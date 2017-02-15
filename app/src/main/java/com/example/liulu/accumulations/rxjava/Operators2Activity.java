package com.example.liulu.accumulations.rxjava;

import android.support.v4.app.FragmentManager;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.wiget.Log;
import com.example.liulu.accumulations.wiget.LogFragment;
import com.example.liulu.accumulations.wiget.LogWrapper;
import com.example.liulu.accumulations.wiget.MessageOnlyLogFilter;

public class Operators2Activity extends BaseActivity {

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        Operators2Fragment fragment = new Operators2Fragment();
        manager.beginTransaction().replace(R.id.sample_content_fragment, fragment).commit();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_operators2;
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

        Log.d("liulu", "Ready");
    }
}
