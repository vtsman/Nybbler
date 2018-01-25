package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class DoubleConstant extends Constant{
	public double x;
	public DoubleConstant(double v) {
		this.x = v;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.INTEGER);
		bos.writeDouble(this.x);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof DoubleConstant) {
			return ((DoubleConstant)o).x == this.x;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "DoubleConstant(" + x + ")";
	}
}
