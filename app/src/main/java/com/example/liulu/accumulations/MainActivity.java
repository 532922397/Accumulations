package com.example.liulu.accumulations;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.liulu.accumulations.androidfive.FiveActivity;
import com.example.liulu.accumulations.animation.AnimationActivity;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.customview.CustomviewActivity;
import com.example.liulu.accumulations.integeration.IntegerationActivity;
import com.example.liulu.accumulations.other.OtherActivity;

import org.xml.sax.XMLReader;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.btn_customview)
    Button btnCustomview;
    @Bind(R.id.btn_intgeration)
    Button btnIntgeration;
    @Bind(R.id.btn_intent)
    Button btnIntent;
    @Bind(R.id.btn_other)
    Button btnOther;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void initData() {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                return null;
            }
        };
        Html.TagHandler tag = new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Spanned spanned = Html.fromHtml("", 0, imageGetter, tag);
            Log.e("TAG", "spanned==" + spanned);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_animation)
    public void goToAnimation(View view) {
        goToActivity(AnimationActivity.class);
    }

    @OnClick(R.id.btn_five)
    public void goToFive(View view) {
        goToActivity(FiveActivity.class);
    }

    @OnClick(R.id.btn_customview)
    public void goToCustomview(View view) {
        goToActivity(CustomviewActivity.class);
    }

    @OnClick(R.id.btn_intgeration)
    public void goToIntgeration(View view) {
        goToActivity(IntegerationActivity.class);
    }

    @OnClick(R.id.btn_intent)
    public void goToIntent(View view) {
        goToActivity(IntentActivity.class);
    }

    @OnClick(R.id.btn_other)
    public void goToOther(View view) {
        goToActivity(OtherActivity.class);
    }

}
