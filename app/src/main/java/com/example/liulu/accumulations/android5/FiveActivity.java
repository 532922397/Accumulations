package com.example.liulu.accumulations.android5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FiveActivity extends BaseActivity {


    @Bind(R.id.btn_snackbar)
    Button btnSnackbar;
    @Bind(R.id.btn_floatingbar)
    FloatingActionButton floatingbar;
    @Bind(R.id.activity_main)
    ScrollView activityMain;

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_five;
    }

    @OnClick({R.id.btn_snackbar, R.id.btn_floatingbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_snackbar:
                showSnackBar();
                break;
            case R.id.btn_floatingbar:

                break;
        }
    }

    private void showSnackBar() {
        Snackbar.make(btnSnackbar, "snackbar", Snackbar.LENGTH_SHORT)
                .setAction("点我!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityMain.setBackground(getResources().getDrawable(R.color.colorAccent));
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.light_white))
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
