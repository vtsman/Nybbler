package com.stmartin.nybbler.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ClassByteOutputStream extends ByteArrayOutputStream{
	//store values as big-endian
	public void writeByte(byte b) {
		this.write(b);
	}

	public void writeShort(short b) {
		writeByte((byte)(b >> 8));
		writeByte((byte)(b & 0xff));
	}
	
	public void writeInt(int b) {
		writeShort((short)(b >> 16));
		writeShort((short)(b & 0xffff));
	}
	
	public void writeLong(long b) {
		writeInt((int)(b >> 32));
		writeInt((int)(b & 0xffffffff));
	}
	
	public void writeFloat(float b) {
		writeInt(Float.floatToRawIntBits(b));
	}
	
	public void writeDouble(double b) {
		writeLong(Double.doubleToRawLongBits(b));
	}
	
	public void writeUTF(String s) {
		this.writeByteArray(StringEncoder.encode(s));
	}
	
	public void writeByteArray(byte[] b) {
		try {
			this.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeShortArray(short[] b) {
		for(short s : b) {
			this.writeShort(s);
		}
	}
	
	public void writeIntArray(int[] b) {
		for(int s : b) {
			this.writeInt(s);
		}
	}
	
	public void writeLongArray(long[] b) {
		for(long s : b) {
			this.writeLong(s);
		}
	}
	
	public void writeFloatArray(float[] b) {
		for(float s : b) {
			this.writeFloat(s);
		}
	}
	
	public void writeDoubleArray(double[] b) {
		for(double s : b) {
			this.writeDouble(s);
		}
	}

}
