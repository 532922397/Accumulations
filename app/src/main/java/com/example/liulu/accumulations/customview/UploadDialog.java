package com.example.liulu.accumulations.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.liulu.accumulations.R;

/**
 * Created by liulu on 2017/2/17.
 */

public class UploadDialog {

    private Dialog progressDialog;

    /**
     * 显示dialog
     *
     * @param context
     * @param msg     内容
     */
    public void showDialog(Context context, String msg) {
        progressDialog = new Dialog(context, R.style.progressDialog);
        progressDialog.setContentView(R.layout.upload_progress);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) progressDialog.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    /**
     * 关闭dialog
     */
    public void cancelDialog() {
        progressDialog.cancel();
    }


}
