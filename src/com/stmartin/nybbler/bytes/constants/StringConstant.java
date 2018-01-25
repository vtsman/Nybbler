package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class StringConstant extends Constant{
	public UTF8Constant utf;
	public StringConstant(UTF8Constant c) {
		this.utf = c;
	}
	
	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.STRING);
		try {
			bos.writeShort(this.utf.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof StringConstant) {
			return ((StringConstant)o).utf == this.utf;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "StringConstant(" + utf.s + ")";
	}
}
