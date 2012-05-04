package com.syn3rgy.tools;

import java.util.List;

public class ListFunctions {
	
	public static <T> String listToString(List<T> list, String separator) {
		String str = "";
		for(int i = 0; i < list.size(); i++) {
			if(i > 0) {
				str += separator;
			}
			
			str += list.get(i).toString();
		}
		return str;
	}
}
