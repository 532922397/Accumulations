package com.example.liulu.accumulations.android5;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 可能遇到加载异常，因为design需要设置主题为父类主题
 */
public class FiveActivity extends BaseActivity {


    @Bind(R.id.btn_snackbar)
    Button btnSnackbar;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    @Bind(R.id.floatingbar)
    FloatingActionButton floatingbar;
    private int a;

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_five;
    }

    @OnClick({R.id.btn_snackbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_snackbar:
                showSnackBar();
                break;
            case R.id.floatingbar:
                floatingbar.hide();

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
}
