package com.acdt.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToNumberPart {
    /**
     * 获取字符串中的数字
     *
     * @param str 含有数字的字符串
     * @return 返回字符串中的数字
     */
    public static String toNumberPart(String str) {
        String content = str.trim();
        Pattern p = Pattern.compile("([-+])?\\d+(\\.\\d+)?");
        Matcher matcher = p.matcher(content);
        return matcher.find() ? matcher.group(0) : "";
    }
}
