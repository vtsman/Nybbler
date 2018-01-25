package com.stmartin.nybbler;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import com.stmartin.nybbler.bytes.ClassAccess;
import com.stmartin.nybbler.bytes.ClassGenerator;
import com.stmartin.nybbler.bytes.DescriptorEncoder;
import com.stmartin.nybbler.bytes.attributes.CodeAttribute;
import com.stmartin.nybbler.bytes.constants.ConstantDoubleOwnException;
import com.stmartin.nybbler.bytes.constants.UTF8Constant;
import com.stmartin.nybbler.bytes.method.Method;
import com.stmartin.nybbler.bytes.method.MethodAccessFlags;
import com.stmartin.nybbler.bytes.method.bytecode.*;

public class Main {
	//TODO type objects
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String name = "test";
		ClassGenerator testClass = new ClassGenerator(name, ClassAccess.PUBLIC);
		testClass.addInterface(TestInterface.class.getName());

		UTF8Constant mname = new UTF8Constant("test");
		UTF8Constant mtype = new UTF8Constant(DescriptorEncoder.encodeMethodHeader(void.class));

		try {
			testClass.addConstant(mtype);
			testClass.addConstant(mname);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		
		CodeAttribute ca = new CodeAttribute();
		try {
			ca.addInstruction(new GetStaticInstruction(testClass.getFieldReference(System.class.getField("out"))));
			ca.addInstruction(new LDCInstruction(testClass.getString("Hello World!")));
			ca.addInstruction(new InvokeVirtualInstruction(testClass.getMethodReference(System.out.getClass().getMethod("println", String.class))));
			ca.addInstruction(SimpleInstruction.RETURN);
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Method m = new Method((short)(MethodAccessFlags.PUBLIC | MethodAccessFlags.STATIC), mname, mtype, ca);
		
		testClass.addMethod(m);
		
		byte[] gend = testClass.encode();
		System.out.println(testClass);
		//System.out.println(Arrays.toString(gend));
		TestLoader tl = new TestLoader();
		Class c = tl.loadClass(name, gend);
		//System.out.println(c);
		//System.out.println(c.getSuperclass());
		//System.out.println(c.getInterfaces()[0]);
		try {
			c.getMethods()[0].invoke(c);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
