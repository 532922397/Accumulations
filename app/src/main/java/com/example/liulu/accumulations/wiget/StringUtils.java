package com.example.liulu.accumulations.wiget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by liulu on 2017/2/27
 */

public class StringUtils {
    /**
     * 过滤出数字英文汉字以及制定字符
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
//        String regEx = "[/\\：:*_|\"\n\t]"; //要过滤掉的字符
//        String regEx ="[`~!@#$%^&*()+=|{}';',\\[\\].<>/?~！@#￥%……&（）——+|{}【】‘；”“’。，、？]";
        String regEx = "[^(a-zA-Z0-9\\u4e00-\\u9fa5\\？?))]";


        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 过滤出数字
     */
    public static String filterNumber(String number) {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     * 过滤出字母
     */
    public static String filterAlphabet(String alph) {
        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }

    /**
     * 过滤出中文
     */
    public static String filterChinese(String chin) {
        chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
        return chin;
    }

    /**
     * 过滤出字母、数字和中文
     */
    private String first;

    public static String filter(String character) {
        String s = character.replaceAll("[^(a-z0-8\\u4e00-\\u9fa5]", "");
        return s;
    }
}