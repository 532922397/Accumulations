package com.example.liulu.accumulations.other;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.wiget.ParseHtmlToList;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class XMLActivity extends BaseActivity {
    @Bind(R.id.tv_xml)
    TextView tvXml;
    private String testXML = "<span>游客</span>查看了<a class=  id  href = www.baidu.com>商品</a>";


    @Override
    protected void initData() {
        tvXml.setText(testXML);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_xml;
    }

    @OnClick(R.id.btn_translate)
    public void translate(View view) {
        List<String> a = ParseHtmlToList.getAHref(testXML);
        if (a.isEmpty()) {
            return;
        }
        Log.e("liulu", a.get(0));
        Log.e("liulu", "得到完整文字==" + Html.fromHtml(testXML));

    }
}
