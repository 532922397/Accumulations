package com.example.liulu.accumulations.other;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @Bind(R.id.et_show)
    EditText etShow;
    @Bind(R.id.btn_change)
    Button btnChange;
    @Bind(R.id.show)
    TextView show;

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_change)
    public void change(View v) {
        try {
            String s = PinyinHelper.convertToPinyinString(etShow.getText().toString(), "", PinyinFormat.WITHOUT_TONE);

//            String s = PinyinHelper.getShortPinyin(etShow.getText().toString());
            show.setText(s);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        // String pingYin = PinYinUtils.getFullSpell("刘录");
        // show.setText(Pinyin.toPinyin(etShow.getText().toString(),""));

    }


    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }


}
