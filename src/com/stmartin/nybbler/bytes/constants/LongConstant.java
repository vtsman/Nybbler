package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class LongConstant extends Constant{
	public long x;
	public LongConstant(long v) {
		this.x = v;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.INTEGER);
		bos.writeLong(this.x);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof LongConstant) {
			return ((LongConstant)o).x == this.x;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "LongConstant(" + x + ")";
	}

}
