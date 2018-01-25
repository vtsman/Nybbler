package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class NameAndTypeConstant extends Constant{
	public UTF8Constant name;
	public UTF8Constant type;
	public NameAndTypeConstant(UTF8Constant name, UTF8Constant type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.NAME_AND_TYPE);
		try {
			bos.writeShort(this.name.getIndex());
			bos.writeShort(this.type.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof NameAndTypeConstant) {
			return ((NameAndTypeConstant)o).name.equals(this.name) && ((NameAndTypeConstant)o).type.equals(this.type);
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "NameAndTypeConstant(" + name.s + ": " + type.s + ")";
	}
}
