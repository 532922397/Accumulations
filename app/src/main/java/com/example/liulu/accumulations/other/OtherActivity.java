package com.example.liulu.accumulations.other;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.android7.NotificationActivity;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.databinding.DataBindingActivity;
import com.example.liulu.accumulations.view.NullMenuEditText;

import butterknife.Bind;
import butterknife.OnClick;

import static com.example.liulu.accumulations.R.id.btn_test_binding;
import static com.example.liulu.accumulations.wiget.StringUtils.StringFilter;


public class OtherActivity extends BaseActivity implements TextWatcher {
    @Bind(R.id.tv_show)
    TextView tvShow;
    private String str = "流<路a1aa123_:：*?==><";
    @Bind(R.id.et_show)
    NullMenuEditText etShow;
    private String filterNick;
    private String editable;
    private boolean maxC;
    private boolean minC;
    private boolean empC;

    @Override
    protected void initData() {
        etShow.addTextChangedListener(this);
        etShow.setText("abcdefghijkmlnopqrst");
        etShow.setFocusableInTouchMode(true);
        etShow.requestFocus();
        etShow.setSelection(etShow.getText().toString().length());
    }


    @Override
    public int getLayout() {
        return R.layout.activity_other;
    }

    @OnClick({R.id.btn_test_sub, R.id.btn_xml, R.id.btn_test_touch, R.id.btn_save_photo, R.id.btn_notification, R.id.btn_test_binding, R.id.btn_pinyin_search, R.id.btn_StringSpan, R.id.btn_test_layout})
    public void XML(View view) {
        switch (view.getId()) {
            case R.id.btn_xml:
                goToActivity(XMLActivity.class);
                break;
            case R.id.btn_test_touch:
                goToActivity(TouchActivity.class);
                break;
            case R.id.btn_save_photo:
                goToActivity(SavePhotoActivity.class);
                break;
            case R.id.btn_pinyin_search:
                goToActivity(SearchActivity.class);
                break;
            case R.id.btn_StringSpan:
                goToActivity(IconFontActivity.class);
                break;
            case R.id.btn_test_layout:
                goToActivity(TestLayoutActivity.class);
                break;
            case R.id.btn_notification:
                goToActivity(NotificationActivity.class);
                break;
            case btn_test_binding:
                goToActivity(DataBindingActivity.class);
                break;
            case R.id.btn_test_sub:
            goToActivity(SubActivity.class);
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(etShow.getText()) && !TextUtils.isEmpty(etShow.getText().toString().trim())) {
            editable = etShow.getText().toString();

            if ((editable.trim().length() > 20)) {
                // 筛选指定字符
                filterNick = StringFilter(editable);
                if (!editable.equals(filterNick)) {
                    maxC = true;
                }
                etShow.setText(etShow.getText().toString().substring(0, 20));
                etShow.setSelection(etShow.getText().toString().length());
                tvShow.setVisibility(View.VISIBLE);
            } else {
                // 筛选指定字符
                filterNick = StringFilter(editable);
                if (!editable.equals(filterNick)) {
                    minC = true;
                    empC = true;
                    tvShow.setVisibility(View.VISIBLE);
                    etShow.setText(filterNick);
                    etShow.setSelection(filterNick.length()); //光标置后
                } else if (maxC) {
                    tvShow.setVisibility(View.VISIBLE);
                    maxC = false;
                    empC = false;
                } else if (minC) {
                    tvShow.setVisibility(View.VISIBLE);
                    minC = false;
                    empC = false;
                } else {
                    tvShow.setVisibility(View.GONE);
                }
            }


        } else if (empC) {
            tvShow.setVisibility(View.VISIBLE);
            minC = false;
            maxC = false;
            empC = false;
        } else {
            tvShow.setVisibility(View.GONE);
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
