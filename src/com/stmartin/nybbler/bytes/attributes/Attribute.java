package com.stmartin.nybbler.bytes.attributes;

import com.stmartin.nybbler.utils.ClassByteOutputStream;

public abstract class Attribute {
	public abstract void serialize(ClassByteOutputStream bos);
}
