package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class InterfaceMethodRefConstant extends Constant{
	public ClassConstant cc;
	public NameAndTypeConstant ntc;
	public InterfaceMethodRefConstant(ClassConstant cc, NameAndTypeConstant ntc) {
		this.cc = cc;
		this.ntc = ntc;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.INTERFACE_METHOD_REF);
		try {
			bos.writeShort(cc.getIndex());
			bos.writeShort(ntc.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof InterfaceMethodRefConstant) {
			return ((InterfaceMethodRefConstant)o).cc.equals(this.cc) && ((InterfaceMethodRefConstant)o).ntc.equals(this.ntc);
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "InterfaceMethodRef(" + cc.toString() + ", " + ntc.toString() + ")";
	}
	
}
