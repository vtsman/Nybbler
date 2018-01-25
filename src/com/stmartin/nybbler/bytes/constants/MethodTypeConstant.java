package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class MethodTypeConstant extends Constant {
	public UTF8Constant type;

	public MethodTypeConstant(UTF8Constant type) {
		this.type = type;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.write(ConstantClasses.METHOD_TYPE);
		try {
			bos.writeShort(this.type.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MethodTypeConstant) {
			return ((MethodTypeConstant) o).type == this.type;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "UTF8Constant(" + type.s + ")";
	}

}