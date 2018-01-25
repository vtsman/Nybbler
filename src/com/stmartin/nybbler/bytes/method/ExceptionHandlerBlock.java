package com.stmartin.nybbler.bytes.method;

import java.util.ArrayList;

import com.stmartin.nybbler.bytes.constants.ClassConstant;
import com.stmartin.nybbler.bytes.constants.UnownedConstantException;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class ExceptionHandlerBlock {
	private short start, end = -1;
	private ArrayList<ExceptionHandler> handlers = new ArrayList<ExceptionHandler>();
	
	public ExceptionHandlerBlock(short start) {
		this.start = start;
	}
	
	public void addHandler(ClassConstant cc, short ptr) {
		this.handlers.add(new ExceptionHandler(cc, ptr));
	}
	
	public void serialize(ClassByteOutputStream bos) throws UnfinishedExceptionHandlerBlockException, EmptyExceptionHandlerListException, UnownedConstantException {
		if(end == -1) {
			throw new UnfinishedExceptionHandlerBlockException("Error: incomplete exception handler");
		}
		if(handlers.size() == 0) {
			throw new EmptyExceptionHandlerListException("Error: no exception handlers defined for exception handler block");
		}
		for(ExceptionHandler eh : handlers) {
			bos.writeShort(start);
			bos.writeShort(end);
			bos.writeShort(eh.type.getIndex());
			bos.writeShort(eh.ptr);
		}
	}
	
	public short getHandlerBlockLength() {
		return (short) this.handlers.size();
	}
	
	private final class ExceptionHandler{
		public final ClassConstant type;
		public final short ptr;
		public ExceptionHandler(ClassConstant cc, short hdl) {
			this.type = cc;
			this.ptr = hdl;
		}
	}
}
