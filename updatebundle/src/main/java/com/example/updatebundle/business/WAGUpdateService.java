package com.example.updatebundle.business;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.updatebundle.model.WAGUpdateInfo;
import com.example.updatebundle.utils.WAGDiffUtils;
import com.example.updatebundle.utils.WAGFileUtils;
import com.example.updatebundle.utils.WAGSPUtils;
import com.example.updatebundle.utils.WAGUpdateConstants;
import com.example.updatebundle.utils.WAGUpdateUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liulu on 2017/1/3.
 */
public class WAGUpdateService extends IntentService {

    private String zip = "newBundleZip";

    public WAGUpdateService() {
        super("WAGUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String url = intent.getStringExtra("url");
            String md5 = intent.getStringExtra("md5");
            if (!TextUtils.isEmpty(url)) {
                // 开始下载
                startUpdate(url, md5);
            }
        }
    }

    private void startUpdate(String url, String md5) {
        OkHttpClient okHttpClient = null;
        Request request = null;
        Response response = null;
        try {
            okHttpClient = new OkHttpClient.Builder().build();
            request = new Request.Builder().url(url).build();
            response = okHttpClient.newCall(request).execute();
            if (response != null && response.code() == 200 && response.body() != null) {
                // 把zip写入到文件
                if (response.body().byteStream() != null && !TextUtils.isEmpty(md5)) {
                    writeZIPToFile(response.body().byteStream(), md5);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeZIPToFile(InputStream inputStream, String md5) {

        try {
            WAGFileUtils.IS2SD(inputStream, WAGUpdateConstants.FILE_PATH, zip);// 写入zip
            String fileMD5 = WAGFileUtils.getFileMD5(WAGUpdateConstants.ZIP_PATH);
            if (md5.equals(fileMD5)) {// 校验5
                boolean unZipFolder = WAGFileUtils.UnZipFolder(WAGUpdateConstants.ZIP_PATH, WAGUpdateConstants.FILE_PATH);// 解压zip
                if (unZipFolder) {
                    merge();// 开始merge
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            WAGFileUtils.delete(WAGUpdateConstants.ZIP_PATH);// 出错删除zip包
        }
    }

    private void merge() {

        String patchs = WAGFileUtils.readFileToString(WAGUpdateConstants.PATCH_PATH);
        /*得到新的bundle字符串*/
        LinkedList<diff_match_patch.Patch> patches = WAGDiffUtils.textToPatchs(patchs);
        // 新bundle的String形式
        String newBundle = WAGDiffUtils.getNewBundle(patches, WAGFileUtils.getFromAssets(WAGUpdateConstants.COMMON_BUNDLE, WAGUpdateUtil.getApplicationContext()));
        if (TextUtils.isEmpty(newBundle)) {
            return;
        }
        /*merge之前先重命名*/
        if (WAGFileUtils.isExist(WAGUpdateConstants.INDEX_BUNDLE_PATH)) {
            WAGFileUtils.renameFile(WAGUpdateConstants.FILE_PATH, WAGUpdateConstants.INDEX_FILE_NAME, WAGUpdateConstants.TEMP_FILE_NAME);
        }
        /*写入到文件*/
        boolean writeComplete = WAGFileUtils.WriteStringToFile(WAGUpdateConstants.INDEX_BUNDLE_PATH, newBundle);
        if (writeComplete) {
            if (WAGFileUtils.isExist(WAGUpdateConstants.TEMP_FILE_PATH)) {
                WAGFileUtils.deleteFile(WAGUpdateConstants.TEMP_FILE_PATH);
            }
            saveJson();
        } else {// 删除并恢复
            if (WAGFileUtils.isExist(WAGUpdateConstants.INDEX_BUNDLE_PATH)) {
                WAGFileUtils.deleteFile(WAGUpdateConstants.INDEX_BUNDLE_PATH);
            }
            WAGFileUtils.renameFile(WAGUpdateConstants.FILE_PATH, WAGUpdateConstants.TEMP_FILE_NAME, WAGUpdateConstants.INDEX_FILE_NAME);
        }
        WAGFileUtils.deleteFile(WAGUpdateConstants.ZIP_PATH);

    }

    private void saveJson() {
        // 这里从sp中取出之前的
        Gson gson = new Gson();
        WAGUpdateInfo WAGUpdateInfo = new WAGUpdateInfo();
        int versionCode = WAGSPUtils.getVersionCode(WAGUpdateUtil.getApplicationContext());
        Log.e("liulu", "versionCode==" + versionCode);
        WAGUpdateInfo.setAppVersion(versionCode + "");
        WAGUpdateInfo.setBundleVersion("1.0.1");// 这里需定义规则版本的迭代
        String updateJson = gson.toJson(WAGUpdateInfo);
        // 存储在sp中
        WAGSPUtils.putString(WAGUpdateUtil.getApplicationContext(), updateJson);

    }
}
