package com.stmartin.nybbler.bytes.method.bytecode;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public interface Instruction {
	public void serialize(ClassByteOutputStream bos);
}
