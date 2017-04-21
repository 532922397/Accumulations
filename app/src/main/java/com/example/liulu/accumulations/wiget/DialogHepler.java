package com.example.liulu.accumulations.wiget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.example.liulu.accumulations.R;

/**
 * Created by liulu on 2017/3/23
 */

public class DialogHepler {


    private  Context context;

    public DialogHepler(Context context) {
        this.context = context;
    }

    public void show() {

    }

    public void dismiss() {

    }

    public static Dialog createProgressDialog(Context context, String msg) {
        Dialog progressDialog = new Dialog(context, R.style.progressDialog);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        progressDialog.setCancelable(true);
        return progressDialog;
    }

}
