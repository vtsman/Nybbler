package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class FloatConstant extends Constant{
	public float x;
	public FloatConstant(float v) {
		this.x = v;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.INTEGER);
		bos.writeFloat(this.x);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FloatConstant) {
			return ((FloatConstant)o).x == this.x;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "FloatConstant(" + x + ")";
	}
}
