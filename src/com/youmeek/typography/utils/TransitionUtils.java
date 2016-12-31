package com.youmeek.typography.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransitionUtils {
	private static final Pattern CJK_ANS = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([a-z0-9`~@\\$%\\^&\\*\\-_\\+=\\|\\\\/])", 2);
	private static final Pattern ANS_CJK = Pattern.compile("([a-z0-9`~!\\$%\\^&\\*\\-_\\+=\\|\\\\;:,\\./\\?])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])", 2);
	private static final Pattern CJK_QUOTE = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\"'])");
	private static final Pattern QUOTE_CJK = Pattern.compile("([\"'])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern FIX_QUOTE = Pattern.compile("([\"'])(\\s*)(.+?)(\\s*)([\"'])");
	private static final Pattern CJK_BRACKET_CJK = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\\({\\[]+(.*?)[\\)}\\]]+)([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern CJK_BRACKET = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])([\\(\\){}\\[\\]<>])");
	private static final Pattern BRACKET_CJK = Pattern.compile("([\\(\\){}\\[\\]<>])([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");
	private static final Pattern FIX_BRACKET = Pattern.compile("([(\\({\\[)]+)(\\s*)(.+?)(\\s*)([\\)}\\]]+)");
	private static final Pattern CJK_HASH = Pattern.compile("([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])(#(\\S+))");
	private static final Pattern HASH_CJK = Pattern.compile("((\\S+)#)([\\p{InHiragana}\\p{InKatakana}\\p{InBopomofo}\\p{InCJKCompatibilityIdeographs}\\p{InCJKUnifiedIdeographs}])");

	public static String spacingText(String text) {
		Matcher matcher = CJK_QUOTE.matcher(text);
		text = matcher.replaceAll("$1 $2");

		matcher = QUOTE_CJK.matcher(text);
		text = matcher.replaceAll("$1 $2");

		matcher = FIX_QUOTE.matcher(text);
		text = matcher.replaceAll("$1$3$5");

		String oldText = text;
		matcher = CJK_BRACKET_CJK.matcher(text);
		String newText = matcher.replaceAll("$1 $2 $4");
		text = newText;
		if (oldText.equals(newText)) {
			matcher = CJK_BRACKET.matcher(text);
			text = matcher.replaceAll("$1 $2");

			matcher = BRACKET_CJK.matcher(text);
			text = matcher.replaceAll("$1 $2");
		}
		matcher = FIX_BRACKET.matcher(text);
		text = matcher.replaceAll("$1$3$5");

		matcher = CJK_HASH.matcher(text);
		text = matcher.replaceAll("$1 $2");

		matcher = HASH_CJK.matcher(text);
		text = matcher.replaceAll("$1 $3");

		matcher = CJK_ANS.matcher(text);
		text = matcher.replaceAll("$1 $2");

		matcher = ANS_CJK.matcher(text);
		text = matcher.replaceAll("$1 $2");
		
		return text;
	}
}
