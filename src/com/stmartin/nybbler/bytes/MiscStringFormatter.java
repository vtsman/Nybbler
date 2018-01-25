package com.stmartin.nybbler.bytes;

public class MiscStringFormatter {
	public static String indent(String s) {
		StringBuilder sb = new StringBuilder();
		for(String st : s.split("\n")) {
			sb.append("\t" + st + "\n");
		}
		String out = sb.toString();
		return out.substring(0, out.length() - 1);
	}
}
