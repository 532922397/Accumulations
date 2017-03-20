package com.example.updatebundle.utils;

import android.text.TextUtils;

import com.example.updatebundle.business.diff_match_patch;

import java.util.LinkedList;

/**
 * Created by liulu on 2017/1/4.
 */

public class WAGDiffUtils {
    public static diff_match_patch dmp = new diff_match_patch();

    /**
     * 把patch转为Stirng形式
     *
     * @param patches 补丁
     * @return 返回String形式patchs
     */
    public static String patchToText(LinkedList<diff_match_patch.Patch> patches) {

        if (patches != null) {
            return dmp.patch_toText(patches);
        } else {
            return "";
        }
    }

    /**
     * 把string转为linkList补丁
     *
     * @param patchs String形式补丁
     * @return 返回值
     */
    public static LinkedList<diff_match_patch.Patch> textToPatchs(String patchs) {

        if (!TextUtils.isEmpty(patchs)) {
            return (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(patchs);
        } else {
            return null;
        }

    }

    /**
     * 得到补丁文件
     *
     * @param baseText  框架层
     * @param indexText 总bundle
     * @return 补丁
     */
    public static LinkedList<diff_match_patch.Patch> patchMake(String baseText, String indexText) {

        return dmp.patch_make(baseText, indexText);

    }

    public static String getNewBundle(LinkedList<diff_match_patch.Patch> patches, String baseText) {
        if (TextUtils.isEmpty(baseText)) {
            return null;
        }
        Object[] objects = dmp.patch_apply(patches, baseText);
        return (String) objects[0];
    }
}
