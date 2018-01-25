package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class ClassConstant extends Constant{
	public UTF8Constant name;
	public ClassConstant(UTF8Constant name) {
		this.name = name;
	}
	
	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.write(ConstantClasses.CLASS);
		try {
			bos.writeShort(this.name.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ClassConstant) {
			return ((ClassConstant)o).name == this.name;
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "ClassConstant(" + name.s + ")";
	}

}