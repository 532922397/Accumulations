package com.example.liulu.accumulations.other;

import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;


public class OtherActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_other;
    }

    @OnClick({R.id.btn_xml,R.id.btn_test_touch,R.id.btn_save_photo,R.id.btn_pinyin_search})
    public void XML(View view) {
        switch (view.getId()) {
            case  R.id.btn_xml:
                goToActivity(XMLActivity.class);
                break;
            case  R.id.btn_test_touch:
                goToActivity(TouchActivity.class);
                break;
            case  R.id.btn_save_photo:
                goToActivity(SavePhotoActivity.class);
                break;
            case R.id.btn_pinyin_search:
                goToActivity(SearchActivity.class);
                break;
        }

    }


}
