package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public abstract class Constant {
	private short index = -1;
	public abstract void serialize(ClassByteOutputStream bos);
	public void own(short index) throws ConstantDoubleOwnException {
		if(this.index != -1) {
			throw new ConstantDoubleOwnException("Error: attemped to own already owned constant");
		}
		this.index = index;
	}
	
	public short getIndex() throws UnownedConstantException {
		if(index == -1) {
			throw new UnownedConstantException("Error: attempted to get index of unowned constant");
		}
		return index;
	}
}
