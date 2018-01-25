package com.stmartin.nybbler;

public class TestLoader extends ClassLoader{
	@SuppressWarnings("rawtypes")
	public Class loadClass(String name, byte[] b) {
		return this.defineClass(name, b, 0, b.length);
	}
}
