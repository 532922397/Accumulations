package com.example.liulu.accumulations.other;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;

/**
 * 1得到svg图
 * 2svg图添加到购物车并加入到项目
 * 3下载文件得到ttf文件放到assets目录
 * 4加载使用
 */
public class IconFontActivity extends BaseActivity {
    @Bind(R.id.name)
    TextView name;
    private String test = "刘录aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Override
    protected void initData() {
        String one = getResources().getString(R.string.one);
        String format = String.format(one, test);
        SpannableString ss = new SpannableString(format);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), test.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        name.setText(ss);
        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        name.setTypeface(fromAsset);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_string_span;
    }
}
