package com.example.liulu.accumulations.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.liulu.accumulations.R;

/**
 * Created by liulu on 2017/2/17.
 */

public class UploadDialog extends Dialog {
    private static UploadDialog progressDialog = null;

    public UploadDialog(Context context) {
        super(context);
    }

    public UploadDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    /**
     * 显示dialog
     *
     * @param context
     * @param textMsg 内容
     */
    public static UploadDialog showDialog(Context context, String textMsg) {
        progressDialog = new UploadDialog(context, R.style.progressDialog);
        progressDialog.setContentView(R.layout.upload_progress);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) progressDialog.findViewById(R.id.tv_loadingmsg);
        tvMsg.setText(textMsg);
        progressDialog.show();

        return progressDialog;
    }

    /**
     * 关闭dialog
     */
    public static void cancelDialog() {
        progressDialog.cancel();
    }


}
