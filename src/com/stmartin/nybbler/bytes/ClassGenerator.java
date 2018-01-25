package com.stmartin.nybbler.bytes;

import java.util.ArrayList;
import java.util.HashMap;

import com.stmartin.nybbler.bytes.attributes.Attribute;
import com.stmartin.nybbler.bytes.constants.*;
import com.stmartin.nybbler.bytes.method.Method;
import com.stmartin.nybbler.utils.ClassByteOutputStream;

public class ClassGenerator {
	private static ArrayList<Constant> initPool = new ArrayList<Constant>();
	private static HashMap<String, Short> attributeTags = new HashMap<String, Short>();
	static {
		String[] vals = new String[] { "ConstantValue", "Code", "StackMapTable", "Exceptions", "InnerClasses",
				"EnclosingMethod", "Synthetic", "Signature", "SourceFile", "SourceDebugExtension", "LineNumberTable",
				"LocalVariableTable", "LocalVariableTypeTable", "Deprecated", "RuntimeVisibleAnnotations",
				"RuntimeInvisibleAnnotations", "RuntimeVisibleParameterAnnotations",
				"RuntimeInvisibleParameterAnnotations", "AnnotationDefault", "BootstrapMethods" };

		for (short i = 0; i < vals.length; i++) {
			UTF8Constant con = new UTF8Constant(vals[i]);
			attributeTags.put(vals[i], (short) (i + 1));
			try {
				con.own((short) (i + 1));
			} catch (ConstantDoubleOwnException e) {
				e.printStackTrace();
			}
			initPool.add(con);
		}
	}

	public static short getAttributeTagIndex(String s) {
		return attributeTags.get(s);
	}

	private ArrayList<Constant> constantPool = new ArrayList<Constant>();
	private String name;
	private short thisConst;
	private short superConst;
	private short major, minor;
	private short flags;
	private ArrayList<ClassConstant> interfaces = new ArrayList<ClassConstant>();
	private ArrayList<Method> methods = new ArrayList<Method>();
	private ArrayList<Field> fields = new ArrayList<Field>();
	private ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	public ClassGenerator(String name, short major, short minor, String thisClass, String superClass,
			short accessFlags) {
		for (int i = 0; i < initPool.size(); i++) {
			this.constantPool.add(initPool.get(i));
		}
		thisClass = thisClass.replaceAll("\\.", "/");
		superClass = superClass.replaceAll("\\.", "/");
		try {

			if (superClass == null) {
				this.superConst = 0;
			} else {
				this.superConst = this.getClass(superClass).getIndex();
			}
			this.thisConst = this.getClass(thisClass).getIndex();
		} catch (UnownedConstantException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.major = major;
		this.minor = minor;
		this.flags = accessFlags;
	}

	public ClassGenerator(String name, short major, short minor, String superClass, short accessFlags) {
		this(name, major, minor, name, superClass, accessFlags);
	}

	public ClassGenerator(String name, short major, short minor, short accessFlags) {
		this(name, major, minor, name, Object.class.getName(), accessFlags);
	}

	public ClassGenerator(String name, short accessFlags) {
		this(name, ClassMajors.JSE_70, (short) 0, accessFlags);
	}

	public void addConstant(Constant constantValue) throws ConstantDoubleOwnException {
		Constant dup = findDuplicate(constantValue);
		if (dup == null) {
			if (constantValue instanceof ClassConstant) {
				Constant udup = findDuplicate(((ClassConstant) constantValue).name);
				if (udup != null) {
					((ClassConstant) constantValue).name = (UTF8Constant) udup;
				}
			}
			if (constantValue instanceof StringConstant) {
				Constant udup = findDuplicate(((StringConstant) constantValue).utf);
				if (udup != null) {
					((StringConstant) constantValue).utf = (UTF8Constant) udup;
				}
			}
			if (constantValue instanceof MethodTypeConstant) {
				Constant udup = findDuplicate(((MethodTypeConstant) constantValue).type);
				if (udup != null) {
					((MethodTypeConstant) constantValue).type = (UTF8Constant) udup;
				}
			}
			if (constantValue instanceof NameAndTypeConstant) {
				{
					Constant udup = findDuplicate(((NameAndTypeConstant) constantValue).name);
					if (udup != null) {
						((NameAndTypeConstant) constantValue).name = (UTF8Constant) udup;
					}
				}
				{
					Constant udup = findDuplicate(((NameAndTypeConstant) constantValue).type);
					if (udup != null) {
						((NameAndTypeConstant) constantValue).type = (UTF8Constant) udup;
					}
				}
			}
			if (constantValue instanceof MethodRefConstant) {
				{
					Constant udup = findDuplicate(((MethodRefConstant) constantValue).cc);
					if (udup != null) {
						((MethodRefConstant) constantValue).cc = (ClassConstant) udup;
					}
				}
				{
					{
						Constant udup = findDuplicate(((MethodRefConstant) constantValue).ntc);
						if (udup != null) {
							((MethodRefConstant) constantValue).ntc = (NameAndTypeConstant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((MethodRefConstant) constantValue).ntc.name);
						if (udup != null) {
							((MethodRefConstant) constantValue).ntc.name = (UTF8Constant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((MethodRefConstant) constantValue).ntc.type);
						if (udup != null) {
							((MethodRefConstant) constantValue).ntc.type = (UTF8Constant) udup;
						}
					}
				}
			}
			if (constantValue instanceof InterfaceMethodRefConstant) {
				{
					Constant udup = findDuplicate(((InterfaceMethodRefConstant) constantValue).cc);
					if (udup != null) {
						((InterfaceMethodRefConstant) constantValue).cc = (ClassConstant) udup;
					}
				}
				{
					{
						Constant udup = findDuplicate(((InterfaceMethodRefConstant) constantValue).ntc);
						if (udup != null) {
							((InterfaceMethodRefConstant) constantValue).ntc = (NameAndTypeConstant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((InterfaceMethodRefConstant) constantValue).ntc.name);
						if (udup != null) {
							((InterfaceMethodRefConstant) constantValue).ntc.name = (UTF8Constant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((InterfaceMethodRefConstant) constantValue).ntc.type);
						if (udup != null) {
							((InterfaceMethodRefConstant) constantValue).ntc.type = (UTF8Constant) udup;
						}
					}
				}
			}
			if (constantValue instanceof FieldRefConstant) {
				{
					Constant udup = findDuplicate(((FieldRefConstant) constantValue).cc);
					if (udup != null) {
						((FieldRefConstant) constantValue).cc = (ClassConstant) udup;
					}
				}
				{
					{
						Constant udup = findDuplicate(((FieldRefConstant) constantValue).ntc);
						if (udup != null) {
							((FieldRefConstant) constantValue).ntc = (NameAndTypeConstant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((FieldRefConstant) constantValue).ntc.name);
						if (udup != null) {
							((FieldRefConstant) constantValue).ntc.name = (UTF8Constant) udup;
						}
					}
					{
						Constant udup = findDuplicate(((FieldRefConstant) constantValue).ntc.type);
						if (udup != null) {
							((FieldRefConstant) constantValue).ntc.type = (UTF8Constant) udup;
						}
					}
				}
			}
			constantPool.add(constantValue);
			constantValue.own((short) constantPool.size());
		} else {
			try {
				constantValue.own(dup.getIndex());
			} catch (UnownedConstantException e) {
				e.printStackTrace();
			}
		}
	}

	public Constant findDuplicate(Constant c) {
		for (Constant co : this.constantPool) {
			if (co.equals(c)) {
				return co;
			}
		}
		return null;
	}

	public UTF8Constant getUTF8(String s) {
		for (Constant c : this.constantPool) {
			if (c instanceof UTF8Constant) {
				if (((UTF8Constant) c).s.equals(s)) {
					return (UTF8Constant) c;
				}
			}
		}
		UTF8Constant out = new UTF8Constant(s);
		try {
			this.addConstant(out);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		return out;
	}

	public ClassConstant getClass(String s) {
		s = s.replaceAll("\\.", "/");
		for (Constant c : this.constantPool) {
			if (c instanceof ClassConstant) {
				if (((ClassConstant) c).name.s.equals(s)) {
					return (ClassConstant) c;
				}
			}
		}
		ClassConstant out = new ClassConstant(this.getUTF8(s));
		try {
			this.addConstant(out);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		return out;
	}

	public StringConstant searchString(String s) {
		for (Constant c : this.constantPool) {
			if (c instanceof StringConstant) {
				if (((StringConstant) c).utf.s.equals(s)) {
					return (StringConstant) c;
				}
			}
		}
		return null;
	}

	public StringConstant getString(String s) {
		StringConstant search = searchString(s);
		if (search != null) {
			return search;
		}
		StringConstant out = new StringConstant(this.getUTF8(s));
		try {
			this.addConstant(out);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		return out;
	}

	public void addInterface(String s) {
		s = s.replaceAll("\\.", "/");
		for (ClassConstant cc : this.interfaces) {
			if (cc.name.s.equals(s)) {
				return;
			}
		}
		this.interfaces.add(this.getClass(s));
	}

	public void addField(Field f) {
		this.fields.add(f);
	}
	
	public void addMethod(Method m) {
		this.methods.add(m);
	}
	
	public MethodRefConstant getMethodReference(java.lang.reflect.Method m) {
		ClassConstant cc = this.getClass(m.getDeclaringClass().getName());
		NameAndTypeConstant ntc = new NameAndTypeConstant(this.getUTF8(m.getName()), this.getUTF8(DescriptorEncoder.encodeMethodHeader(m)));
		MethodRefConstant mrc = new MethodRefConstant(cc, ntc);
		try {
			this.addConstant(ntc);
			this.addConstant(mrc);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		return mrc;
	}
	
	public FieldRefConstant getFieldReference(java.lang.reflect.Field f) {
		ClassConstant cc = this.getClass(f.getDeclaringClass().getName());
		NameAndTypeConstant ntc = new NameAndTypeConstant(this.getUTF8(f.getName()), this.getUTF8(DescriptorEncoder.encodeFieldType(f)));
		FieldRefConstant frc = new FieldRefConstant(cc, ntc);
		try {
			this.addConstant(ntc);
			this.addConstant(frc);
		} catch (ConstantDoubleOwnException e) {
			e.printStackTrace();
		}
		return frc;
	}

	public byte[] encode() {
		ClassByteOutputStream bos = new ClassByteOutputStream();
		bos.writeInt(0xcafebabe);
		bos.writeShort(this.minor);
		bos.writeShort(this.major);
		bos.writeShort((short) (this.constantPool.size() + 1));
		for (Constant c : this.constantPool) {
			c.serialize(bos);
		}
		bos.writeShort(this.flags);
		bos.writeShort(this.thisConst);
		bos.writeShort(this.superConst);
		bos.writeShort((short) this.interfaces.size());
		for (ClassConstant cc : this.interfaces) {
			try {
				bos.writeShort(cc.getIndex());
			} catch (UnownedConstantException e) {
				e.printStackTrace();
			}
		}
		bos.writeShort((short) this.fields.size());
		for (Field f : this.fields) {
			f.serialize(bos);
		}
		bos.writeShort((short) this.methods.size());
		for (Method m : this.methods) {
			m.serialize(bos);
		}
		bos.writeShort((short) this.attributes.size());
		for (Attribute a : this.attributes) {
			a.serialize(bos);
		}
		return bos.toByteArray();
	}

	@Override
	public String toString() {
		String out = "class " + this.name +":\n";
		StringBuilder members = new StringBuilder();
		members.append("Version: " + this.major + "." + this.minor + "\n");
		members.append("Flags: " + FieldAccessFlags.toString(this.flags) + "\n");
		members.append("This: " + this.constantPool.get(this.thisConst - 1).toString() + "\n");
		members.append("Super: " + this.constantPool.get(this.superConst - 1).toString() + "\n");
		members.append("Constant pool:\n");
		StringBuilder constants = new StringBuilder();
		int i = 0;
		for(Constant c : this.constantPool) {
			constants.append(++i + ": " + c.toString() + "\n");
		}
		members.append(MiscStringFormatter.indent(constants.toString()));
		
		members.append("\nInterfaces:\n");
		StringBuilder interfaces = new StringBuilder();
		for(ClassConstant c : this.interfaces) {
			interfaces.append(c.name.s + "\n");
		}
		members.append(MiscStringFormatter.indent(interfaces.toString()));
		
		members.append("\nMethods:\n");
		StringBuilder methods = new StringBuilder();
		for(Method c : this.methods) {
			methods.append(c.toString() + "\n");
		}
		members.append(MiscStringFormatter.indent(methods.toString()));
		
		out += MiscStringFormatter.indent(members.toString());
		
		return out; // temp
	}
}
