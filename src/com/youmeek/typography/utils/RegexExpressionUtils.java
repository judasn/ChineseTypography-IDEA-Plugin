package com.youmeek.typography.utils;

import java.util.regex.Pattern;

public class RegexExpressionUtils {

	/**
	 * 利用正则表达式替换字符串
	 *
	 * @param str    待替换的字符串
	 * @param regex  正则表达式
	 * @param newStr 用来替换的字符串
	 * @return
	 */
	public static String replace(String str, String regex, String newStr) {
		return Pattern.compile(regex).matcher(str).replaceAll(newStr);
	}
	

}