package io.numis.formatter;

public class FormatHTML {
	
	public static String getFormattedString(String inputString) {
		String formattedString = "";
		for (String str : inputString.split("\n")) {
			formattedString = "<h1>"+str+"</h1><br>" + formattedString;
		}
		return formattedString;
	}
}
