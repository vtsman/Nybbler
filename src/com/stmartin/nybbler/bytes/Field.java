package com.stmartin.nybbler.bytes;

import java.util.ArrayList;

import com.stmartin.nybbler.bytes.attributes.*;
import com.stmartin.nybbler.bytes.constants.UTF8Constant;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class Field {
	private short flags;
	private UTF8Constant name, desc;
	private ArrayList<Attribute> attrib = new ArrayList<Attribute>();
	public Field(short accessFlags, UTF8Constant name, UTF8Constant descriptor) {
		this.flags = accessFlags;
		this.name = name;
		this.desc = descriptor;
	}
	
	public void addAttribute(Attribute a) throws IllegalAttributeException {
		if(!(a instanceof ConstantValueAttribute)) {
			throw new IllegalAttributeException("Error: illegal field attribute of type " + a.getClass().getSimpleName());
		}
		this.attrib.add(a);
	}
	
	public void serialize(ClassByteOutputStream bos) {
		bos.writeShort(flags);
		try {
			bos.writeShort(this.name.getIndex());
			bos.writeShort(this.desc.getIndex());
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
		bos.writeShort((short)attrib.size());
		for(Attribute a : this.attrib) {
			a.serialize(bos);
		}
	}
}
