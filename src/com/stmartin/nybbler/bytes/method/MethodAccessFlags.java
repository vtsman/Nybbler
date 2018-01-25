package com.stmartin.nybbler.bytes.method;

public class MethodAccessFlags {
	/* @formatter:off */
	public static final short PUBLIC       = 0x0001;
	public static final short PRIVATE      = 0x0002;
	public static final short PROTECTED    = 0x0004;
	public static final short STATIC       = 0x0008;
	public static final short FINAL        = 0x0010;
	public static final short SYNCHRONIZED = 0x0020;
	public static final short VOLATILE     = 0x0040;
	public static final short VARARGS      = 0x0080;
	public static final short NATIVE       = 0x0100;
	public static final short ABSTRACT     = 0x0400;
	public static final short STRICT       = 0x0800;
	public static final short SYNTHETIC    = 0x1000;
	/* @formatter:on */
	
	public static String toString(short s) {
		String out = "";
		if((s & PUBLIC) != 0) {
			out += "PUBLIC ";
		}
		if((s & PRIVATE) != 0) {
			out += "PRIVATE ";
		}
		if((s & PROTECTED) != 0) {
			out += "PROTECTED ";
		}
		if((s & STATIC) != 0) {
			out += "STATIC ";
		}
		if((s & FINAL) != 0) {
			out += "FINAL ";
		}
		if((s & SYNCHRONIZED) != 0) {
			out += "SYNCHRONIZED ";
		}
		if((s & VOLATILE) != 0) {
			out += "VOLATILE ";
		}
		if((s & VARARGS) != 0) {
			out += "VARARGS ";
		}
		if((s & NATIVE) != 0) {
			out += "NATIVE ";
		}
		if((s & ABSTRACT) != 0) {
			out += "ABSTRACT ";
		}
		if((s & STRICT) != 0) {
			out += "STRICT ";
		}
		if((s & SYNTHETIC) != 0) {
			out += "SYNTHETIC ";
		}
		return out;
	}
}
