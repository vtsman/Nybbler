package com.stmartin.nybbler.bytes.attributes;

import com.stmartin.nybbler.bytes.ClassGenerator;
import com.stmartin.nybbler.bytes.constants.*;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class ConstantValueAttribute extends Attribute{
	private Constant con;
	public ConstantValueAttribute(Constant c) throws InvalidConstantAttributeException {
		if(c instanceof LongConstant || c instanceof FloatConstant || c instanceof StringConstant || c instanceof IntegerConstant || c instanceof DoubleConstant) {
			this.con = c;
		}
		else {
			throw new InvalidConstantAttributeException("Error: attempted to create ConstantValueException with invalid constant type: " + c.getClass().getSimpleName());
		}
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeShort(ClassGenerator.getAttributeTagIndex("ConstantValue"));
		bos.writeInt(2);
		try {
			bos.writeShort(this.con.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
	}

}
