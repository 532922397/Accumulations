package com.example.updatebundle.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by liulu on 2017/1/4.
 */

public class WAGUpdateConstants {

    // public static String INTERNAL_PATH = MainApplication.getContext().getFilesDir().getAbsolutePath() + File.separator + "bundle" + File.separator;// 内部存储路径;
    public static final String INTERNAL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    public static final String TEMP_FILE_NAME = "temp.bundle";// 跟新时创建的临时文件
    public static final String INDEX_FILE_NAME = "index.bundle";// 跟新时创建的临时文件
    public static final String COMMON_BUNDLE = "common.bundle"; // 基础bundle文件路径
    public static final String FILE_PATH = INTERNAL_PATH + "bundle" + File.separator;// bundle更新总文件夹路径
    public static final String PATCH_PATH = FILE_PATH + "zip/patchs";// bundle更新总文件夹路径
    public static final String INDEX_BUNDLE_PATH = FILE_PATH + "index.bundle";// 更新时老bundle文件路径
    public static final String TEMP_FILE_PATH = FILE_PATH + TEMP_FILE_NAME;// 临时bundle路径
    public static final String ZIP_PATH = INTERNAL_PATH + "bundle" + File.separator + "newBundleZip";// zip路径
    public static final String checkUpdateUrl = "http://10.100.201.145:8080/zip";// 测试url
    // public static final String checkUpdateUrl = "http://192.168.1.112:8080/zip";// 测试url
}
