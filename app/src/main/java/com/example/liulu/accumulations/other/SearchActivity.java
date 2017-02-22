package com.example.liulu.accumulations.other;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.wiget.PinYinUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @Bind(R.id.et_show)
    EditText etShow;
    @Bind(R.id.btn_change)
    Button btnChange;

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_change)

    public void change(View v) {

        String pingYin = PinYinUtils.getFullSpell("刘录");

        Toast.makeText(SearchActivity.this, "包含吗?==" + (pingYin.indexOf("uu") == -1 ? false : true), Toast.LENGTH_SHORT).show();
        etShow.setText(pingYin);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }


}
