package com.example.liulu.accumulations.other;

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


public class OtherActivity extends BaseActivity {
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

}
