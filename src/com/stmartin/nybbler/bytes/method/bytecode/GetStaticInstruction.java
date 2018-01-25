package com.stmartin.nybbler.bytes.method.bytecode;

import com.stmartin.nybbler.bytes.constants.FieldRefConstant;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class GetStaticInstruction implements Instruction{
	private FieldRefConstant c;
	public GetStaticInstruction(FieldRefConstant r) {
		this.c = r;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte((byte) 0xb2);
		try {
			bos.writeShort(c.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String index = "0x";
		try {
			index += Integer.toHexString(c.getIndex() & 0xffff);
		} catch (UnownedConstantException e) {
			index = "(unowned constant)";
			e.printStackTrace();
		}
		return "getstatic " + index + " (" + c.ntc.type.s + ": " + c.cc.name.s + "/" + c.ntc.name.s + ")"; 
	}
}
