package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class IntegerConstant extends Constant{
	public int x;
	public IntegerConstant(int v) {
		this.x = v;
	}
	
	public IntegerConstant(byte v) {
		this.x = v;
	}
	
	public IntegerConstant(short v) {
		this.x = v;
	}
	
	public IntegerConstant(char v) {
		this.x = v;
	}
	
	public IntegerConstant(boolean v) {
		this.x = v ? 1 : 0;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.INTEGER);
		bos.writeInt(this.x);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof IntegerConstant) {
			return ((IntegerConstant)o).x == this.x;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "IntegerConstant(" + x + ")";
	}
}
