package com.stmartin.nybbler.bytes.attributes;

import java.util.ArrayList;

import com.stmartin.nybbler.bytes.ClassGenerator;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.bytes.method.EmptyExceptionHandlerListException;
import com.stmartin.nybbler.bytes.method.ExceptionHandlerBlock;
import com.stmartin.nybbler.bytes.method.UnfinishedExceptionHandlerBlockException;
import com.stmartin.nybbler.bytes.method.bytecode.Instruction;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class CodeAttribute extends Attribute{
	
	private ArrayList<Instruction> bytecode = new ArrayList<Instruction>();
	private ArrayList<ExceptionHandlerBlock> ehs = new ArrayList<ExceptionHandlerBlock>();
	private ArrayList<Attribute> attribs = new ArrayList<Attribute>();
	private ArrayList<LocalVariable> vars = new ArrayList<LocalVariable>();
	private short maxStackSize;
	
	public CodeAttribute(short maxStackSize) {
		this.maxStackSize = maxStackSize;
	}
	
	public CodeAttribute() {
		this(Short.MAX_VALUE);
	}
	
	public void addInstruction(Instruction i) {
		bytecode.add(i);
	}

	@Override
	public void serialize(ClassByteOutputStream bos) {
		bos.writeShort(ClassGenerator.getAttributeTagIndex("Code"));
		int len = 8;
		ClassByteOutputStream instructionStream = new ClassByteOutputStream();
		for(Instruction i : this.bytecode) {
			i.serialize(instructionStream);
		}
		byte[] code = instructionStream.toByteArray();
		len += code.length;
		len += 2;
		short ehblength = 0;
		for(ExceptionHandlerBlock ehb : ehs) {
			ehblength += ehb.getHandlerBlockLength();
		}
		len += 8 * ehblength;
		len += 2;
		ClassByteOutputStream attribStream = new ClassByteOutputStream();
		for(Attribute a : attribs) {
			a.serialize(attribStream);
		}
		byte[] attribBytes = attribStream.toByteArray();
		len += attribBytes.length;
		bos.writeInt(len);
		bos.writeShort(this.maxStackSize);
		bos.writeShort((short) this.vars.size());
		bos.writeInt(code.length);
		bos.writeByteArray(code);
		bos.writeShort(ehblength);
		for(ExceptionHandlerBlock ehb : ehs) {
			try {
				ehb.serialize(bos);
			} catch (UnfinishedExceptionHandlerBlockException | EmptyExceptionHandlerListException
					| UnownedConstantException e) {
				e.printStackTrace();
			}
		}
		bos.writeShort((short) attribs.size());
		bos.writeByteArray(attribBytes);
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("CodeAttribute:\n");
		for(Instruction i : this.bytecode) {
			out.append("\t" + i.toString() + "\n");
		}
		return out.toString();
	}

}
