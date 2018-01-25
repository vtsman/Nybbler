package com.stmartin.nybbler.bytes.method.bytecode;

import com.stmartin.nybbler.bytes.constants.*;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class LDCInstruction implements Instruction{
	private Constant c;
	
	public LDCInstruction(StringConstant s) {
		this.c = s;
	}
	
	public LDCInstruction(IntegerConstant s) {
		this.c = s;
	}
	
	public LDCInstruction(FloatConstant s) {
		this.c = s;
	}
	
	public LDCInstruction(ClassConstant s) {
		this.c = s;
	}
	
	public LDCInstruction(MethodTypeConstant s) {
		this.c = s;
	}
	/*
	public LDCInstruction(MethodHandleConstant s) {
		this.c = s;
	}
	 */
	//TODO add method handles
	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.write(0x12);
		try {
			bos.writeByte((byte)c.getIndex());
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
		
		return "ldc " + index + " (" + this.c + ")";
	}
}
