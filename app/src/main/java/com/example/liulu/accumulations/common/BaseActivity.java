package com.example.liulu.accumulations.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.liulu.accumulations.wiget.Log;
import com.example.liulu.accumulations.wiget.LogWrapper;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initData();
    }
    @Override
    protected  void onStart() {
        super.onStart();
        initializeLogging();
    }

    /** Set up targets to receive log data */
    public void initializeLogging() {
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        Log.i("liulu", "Ready");
    }

    protected abstract void initData();

    public abstract int getLayout();

    public void goToActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
