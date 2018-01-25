package com.stmartin.nybbler.bytes.method.bytecode;

import com.stmartin.nybbler.bytes.constants.MethodRefConstant;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class InvokeVirtualInstruction implements Instruction{
	private MethodRefConstant c;
	public InvokeVirtualInstruction(MethodRefConstant r) {
		this.c = r;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeByte((byte) 0xb6);
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
		return "invokevirtual " + index + " (" + c.ntc.type.s + ": " + c.cc.name.s + "/" + c.ntc.name.s + ")";
	}

}
