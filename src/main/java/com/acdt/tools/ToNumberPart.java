package com.acdt.tools;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href=https://blog.csdn.net/Chia_Hung_Yeh/article/details/113682614>Chia_Hung_Yeh</a>
 */
public class ToNumberPart {
    /**
     * 获取字符串中的数字
     *
     * @param str 含有数字的字符串
     * @return 返回字符串中的数字
     */
    public static String toNumberPart(@NotNull String str) {
        String content = str.trim();
        Pattern p = Pattern.compile("([-+])?\\d+(\\.\\d+)?");
        Matcher matcher = p.matcher(content);
        return matcher.find() ? matcher.group(0) : "";
    }
}
