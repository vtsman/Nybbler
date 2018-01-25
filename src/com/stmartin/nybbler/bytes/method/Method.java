package com.stmartin.nybbler.bytes.method;

import com.stmartin.nybbler.bytes.MiscStringFormatter;
import com.stmartin.nybbler.bytes.attributes.Attribute;
import com.stmartin.nybbler.bytes.constants.UTF8Constant;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class Method {
	private short flags;
	private UTF8Constant name, type;
	private Attribute[] attribs;
	public Method(short accessFlags, UTF8Constant name, UTF8Constant type, Attribute ... attrs) {
		this.flags = accessFlags;
		this.type = type;
		this.name = name;
		this.attribs = attrs;
		if(attrs == null) {
			this.attribs = new Attribute[] {};
		}
	}
	
	public void serialize(ClassByteOutputStream bos) {
		bos.writeShort(flags);
		try {
			bos.writeShort(this.name.getIndex());
			bos.writeShort(this.type.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
		bos.writeShort((short) this.attribs.length);
		for(Attribute a : this.attribs) {
			a.serialize(bos);
		}
	}
	
	@Override
	public String toString() {
		String out = MethodAccessFlags.toString(flags) + name.s + type.s + "\n";
		StringBuilder ats = new StringBuilder();
		for(Attribute a : this.attribs) {
			ats.append(a.toString() + "\n");
		}
		out += MiscStringFormatter.indent(ats.toString());
		return out;
	}
}
