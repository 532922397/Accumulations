package com.example.liulu.accumulations.other;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 劉錄 on 2017/2/7.
 * activity、view（這裡只是指text等不適說viewgroup父類）與viewgroup相比：
 * 相同：都有onTouchEvent與dispatchTouchEvent
 * 不同：viewgroup擁有onInterceptTouchEvent--事件攔截
 */
public class TouchActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {


    @Bind(R.id.myview)
    TestCustomView myview;
    @Bind(R.id.myviewgroup)
    TestCustomViewGroup myviewgroup;

    @Override
    protected void initData() {
        myviewgroup.setOnClickListener(this);
        myviewgroup.setOnTouchListener(this);
        myview.setOnClickListener(this);
        myview.setOnTouchListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_touch;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myview :
                Log.e("liulu", "activity中view的onClick");
                break;
            case R.id.myviewgroup:
                Log.e("liulu", "activity中viewgroup的onClick");
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("liulu", "activity中onTouchEvent的ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("liulu", "activity中onTouchEvent的ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("liulu", "activity中onTouchEvent的ACTION_MOVE");
                break;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("liulu", "activity中dispatchTouchEvent的ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("liulu", "activity中dispatchTouchEvent的ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("liulu", "activity中dispatchTouchEvent的ACTION_MOVE");
                break;
        }

        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId()==R.id.myview) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("liulu", "activity中view的onTouch的ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("liulu", "activity中view的onTouch的ACTION_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("liulu", "activity中view的onTouch的ACTION_MOVE");
                    break;
            }

        }else if(v.getId()==R.id.myviewgroup) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("liulu", "activity中viewgroup的onTouch的ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("liulu", "activity中viewgroup的onTouch的ACTION_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("liulu", "activity中viewgroup的onTouch的ACTION_MOVE");
                    break;
            }
        }


        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
