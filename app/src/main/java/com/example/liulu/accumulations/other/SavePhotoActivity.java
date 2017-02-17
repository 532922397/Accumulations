package com.example.liulu.accumulations.other;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.customview.UploadDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.OnClick;

import static android.os.Environment.getExternalStorageDirectory;

public class SavePhotoActivity extends BaseActivity {
    private ImageView iv;
    private String BASE_PATH = "file://" + getExternalStorageDirectory() + File.separator + "wuage" + File.separator;

    @Override
    protected void initData() {
        iv = (ImageView) findViewById(R.id.iv);
        iv.setDrawingCacheEnabled(true);
        final Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 保存图片
                File appDir = new File(getExternalStorageDirectory(), "wuage");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(getContentResolver(),
                            file.getAbsolutePath() + File.separator + "wuage", fileName, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 通知图库更新
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(BASE_PATH + fileName)));
                iv.setDrawingCacheEnabled(false);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_save_photo;
    }


    @OnClick(R.id.btn_show)
    public void onClick(View view) {
            UploadDialog.showDialog(getApplicationContext(), "正在上传");

    }
}
