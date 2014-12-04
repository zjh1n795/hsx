package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String UnicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}
	
	public static String upperCaseFirstChar(String str){
		StringBuffer sb = new StringBuffer(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		str = sb.toString();
		return str;
	}
	
	
	public static void main(String args[]){
		System.out.println(StringUtil.upperCaseFirstChar("name"));
	}
	

}
