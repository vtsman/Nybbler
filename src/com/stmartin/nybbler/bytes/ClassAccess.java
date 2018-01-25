package com.stmartin.nybbler.bytes;

public class ClassAccess {
	/* @formatter:off */
	public static final short PUBLIC     = 0x0001;
	public static final short FINAL      = 0x0010;
	public static final short SUPER      = 0x0020;
	public static final short INTERFACE  = 0x0200;
	public static final short ABSTRACT   = 0x0400;
	public static final short SYNTHETIC  = 0x1000;
	public static final short ANNOTATION = 0x2000;
	public static final short ENUM       = 0x4000;
	/* @formatter:on */
}
