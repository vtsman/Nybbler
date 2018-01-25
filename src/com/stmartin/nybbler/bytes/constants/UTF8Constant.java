package com.stmartin.nybbler.bytes.constants;

import com.stmartin.nybbler.utils.ClassByteOutputStream;
import com.stmartin.nybbler.utils.StringEncoder;

public class UTF8Constant extends Constant{
	public final String s;
	public UTF8Constant(String s) {
		this.s = s;
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.write(ConstantClasses.UTF8);
		byte[] encoded = StringEncoder.encode(s);
		bos.writeShort((short)encoded.length);
		bos.writeByteArray(encoded);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof UTF8Constant) {
			return ((UTF8Constant)o).s.equals(this.s);
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "UTF8Constant(" + s + ")";
	}
}
