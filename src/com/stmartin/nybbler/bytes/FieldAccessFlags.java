package com.stmartin.nybbler.bytes;

public class FieldAccessFlags {
	/* @formatter:off */
	public static final short PUBLIC    = 0x0001;
	public static final short PRIVATE   = 0x0002;
	public static final short PROTECTED = 0x0004;
	public static final short STATIC    = 0x0008;
	public static final short FINAL     = 0x0010;
	public static final short VOLATILE  = 0x0040;
	public static final short TRANSIENT = 0x0080;
	public static final short SYNTHETIC = 0x1000;
	public static final short ENUM      = 0x4000;
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
		if((s & VOLATILE) != 0) {
			out += "VOLATILE ";
		}
		if((s & TRANSIENT) != 0) {
			out += "TRANSIENT ";
		}
		if((s & SYNTHETIC) != 0) {
			out += "SYNTHETIC ";
		}
		if((s & ENUM) != 0) {
			out += "ENUM ";
		}
		return out;
	}
}
