package com.uptmr.typography.utils

import java.util.regex.Pattern

object RegexExpressionUtils {

    /**
     * 利用正则表达式替换字符串
     *
     * @param str    待替换的字符串
     * @param regex  正则表达式
     * @param newStr 用来替换的字符串
     * @return
     */
    fun replace(str: String, regex: String, newStr: String): String {
        return Pattern.compile(regex).matcher(str).replaceAll(newStr)
    }
}