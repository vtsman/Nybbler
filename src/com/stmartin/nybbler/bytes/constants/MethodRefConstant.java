package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class MethodRefConstant extends Constant{
	public ClassConstant cc;
	public NameAndTypeConstant ntc;
	public MethodRefConstant(ClassConstant cc, NameAndTypeConstant ntc) {
		this.cc = cc;
		this.ntc = ntc;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.METHOD_REF);
		try {
			bos.writeShort(cc.getIndex());
			bos.writeShort(ntc.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof MethodRefConstant) {
			return ((MethodRefConstant)o).cc.equals(this.cc) && ((MethodRefConstant)o).ntc.equals(this.ntc);
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "MethodRef(" + cc.toString() + ", " + ntc.toString() + ")";
	}
}
