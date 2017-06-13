package com.example.liulu.accumulations.customview;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libflowlayout.FlowLayout;
import com.example.libflowlayout.TagAdapter;
import com.example.libflowlayout.TagFlowLayout;
import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class FlowLayoutActivity extends BaseActivity implements TagFlowLayout.OnTagClickListener {

    @Bind(R.id.et_show)
    EditText etShow;
    @Bind(R.id.tagflowlayout)
    TagFlowLayout tagFlowLayout;
    @Bind(R.id.tv_select)
    TextView tvSelect;

    private List<String> itemList;
    private LayoutInflater layoutInflater;

    @Override
    protected void initData() {
        setData();
        etShow.setSelection(etShow.getText().length());
        layoutInflater = LayoutInflater.from(FlowLayoutActivity.this);
        tagFlowLayout.setAdapter(new TagAdapter<String>(itemList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvFlowlayout = (TextView) layoutInflater.inflate(R.layout.item_flowlayout, tagFlowLayout, false);
                tvFlowlayout.setText(s);
                return tvFlowlayout;
            }
        });
        tagFlowLayout.setOnTagClickListener(this);
       /*
        tagFlowLayout.setMaxSelectCount(10);
        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                tvSelect.setText("选择了==" + selectPosSet.toString());
            }
        });*/
    }

    private void setData() {
        itemList = new ArrayList<>();
        itemList.add("张三");
        itemList.add("李四");
        itemList.add("王五");
        itemList.add("哇你家你看撒空间的把机会富");
        itemList.add("哇你家你看撒空间的把机会富爸爸甲酸钠卡暴风科技百度客服把部分靠近岸边开发部");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_flow_layoutctivity;
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        Editable edit = etShow.getEditableText();//获取EditText的文字
        int index = etShow.getSelectionStart();
        String text = "【" + ((TextView) view).getText().toString() + "】";
        if (index < 0 || index >= edit.length()) {
            edit.append(text);
        } else {
            edit.insert(index, text);//光标所在位置插入文字
        }
        return true;
    }
}
