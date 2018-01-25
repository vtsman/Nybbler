package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class FieldRefConstant extends Constant{
	public ClassConstant cc;
	public NameAndTypeConstant ntc;
	public FieldRefConstant(ClassConstant cc, NameAndTypeConstant ntc) {
		this.cc = cc;
		this.ntc = ntc;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte(ConstantClasses.FIELD_REF);
		try {
			bos.writeShort(cc.getIndex());
			bos.writeShort(ntc.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FieldRefConstant) {
			return ((FieldRefConstant)o).cc.equals(this.cc) && ((FieldRefConstant)o).ntc.equals(this.ntc);
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "FieldRefConstant(" + cc.toString() + ", " + ntc.toString() + ")";
	}
	
}
