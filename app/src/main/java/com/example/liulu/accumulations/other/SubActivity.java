package com.example.liulu.accumulations.other;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class SubActivity extends BaseActivity {
    String text = "abcdefgjgjjas";
    @Bind(R.id.et_sub)
    TextView etSub;
    @Bind(R.id.btn_search)
    Button btnSearch;

    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_sub;
    }

    @OnClick({R.id.et_sub, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                boolean search = search(text, etSub.getText().toString().trim());
                Log.e("结果为==", search + "");
                break;
        }
    }


    public static boolean search(String parent, String sun) {
        StringBuilder sb = new StringBuilder();
        char[] parents = parent.toCharArray();
        char[] suns = sun.toCharArray();
        for (int i = 0; i < sun.length(); i++) {
            for (int j = 0; j < parent.length(); j++) {
                if (parents[j] == suns[i]) {
                    sb.append(suns[i]);
                }
            }

        }
        if (sun.equals(sb.toString())) {
            return true;
        } else {
            return false;
        }
//        int c[][] = new int[a.length() + 1][b.length() + 1];
//        // 定义路线    左上角：0 左边：－1 上边：1
//        // 因为都是从1 下标开始，对String 进行处理
//        int path[][] = new int[a.length() + 1][b.length() + 1];
//        a = "_" + a;
//        b = "_" + b;
//        for (int i = 1; i < a.length(); i++) {
//            for (int j = 1; j < b.length(); j++) {
//                if (a.charAt(i) == b.charAt(j)) {
//                    c[i][j] = c[i - 1][j - 1] + 1;
//                    path[i][j] = 1;
//                } else if (c[i - 1][j] >= c[i][j - 1]) { // 上边>左边
//                    c[i][j] = c[i - 1][j];// 上边
//                    path[i][j] = 2;
//                } else {
//                    c[i][j] = c[i][j - 1];
//                    path[i][j] = 3;// 左边
//                }
//            }//for j
//        }//for i
//        return path;
    }
}
