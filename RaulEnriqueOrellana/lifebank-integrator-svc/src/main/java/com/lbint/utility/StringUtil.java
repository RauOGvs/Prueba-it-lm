package com.lbint.utility;

public class StringUtil {
	public static String concatenate(String... strings){
		StringBuilder builder = new StringBuilder();
		
		for(String item: strings)
			builder.append(item);
		
		return builder.toString();
	}
	public static String concatenate(String a, String b){
		StringBuilder builder = new StringBuilder();
			builder.append(a);
			builder.append(b);
		
		return builder.toString();
	}
}
