package com.example.liulu.accumulations.view;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liulu.accumulations.R;


/**
 * Created by liulu on 2017/3/21
 */

public class CustomerComeView extends RelativeLayout {

    private TextView tvCustomerCome;
    private RelativeLayout rlGoTalk;
    private RelativeLayout customTalkView;

    public CustomerComeView(Context context) {
        this(context, null);
    }

    public CustomerComeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerComeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.customer_come, this);
        tvCustomerCome = (TextView) view.findViewById(R.id.tv_customer_come);
        rlGoTalk = (RelativeLayout) view.findViewById(R.id.rl_go_talk);
        customTalkView = (RelativeLayout) view.findViewById(R.id.custom_talk_view);
    }

    /*设置内容*/
    public void setContentText(String content) {
        tvCustomerCome.setText(content);

    }

    /*设置内容字体颜色*/
    public void setContentTextColor(@ColorInt int color) {
        tvCustomerCome.setTextColor(color);
    }

    /*设置item背景色*/
    public void setCustomTalkViewBackground(@DrawableRes int res) {
        customTalkView.setBackgroundResource(res);
    }

    /*设置是否隐藏下部布局*/
    public void setVisibilityBottom(boolean isVisible) {
        rlGoTalk.setVisibility(isVisible == true ? VISIBLE : INVISIBLE);
    }
}
