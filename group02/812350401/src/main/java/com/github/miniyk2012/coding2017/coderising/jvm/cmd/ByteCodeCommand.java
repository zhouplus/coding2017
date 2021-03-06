package com.github.miniyk2012.coding2017.coderising.jvm.cmd;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantInfo;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;

import java.util.HashMap;
import java.util.Map;


public abstract class ByteCodeCommand {	
	
	String opCode;  // 该指令的助记符
	ClassFile clzFile;
	private int offset;  // 该命令在某方法指令中的偏移量
	
	private static Map<String,String> codeMap = new HashMap<String,String>();   // 二级制码与助记符的对应关系
	
	static{
		codeMap.put("01", "aconst_null");
		
		codeMap.put("BB", "new");
		codeMap.put("37", "lstore");
		codeMap.put("B7", "invokespecial");
		codeMap.put("B6", "invokevirtual");
		codeMap.put("B4", "getfield");
		codeMap.put("B5", "putfield");
		codeMap.put("B2", "getstatic");
		
		codeMap.put("2A", "aload_0");
		codeMap.put("2B", "aload_1");
		codeMap.put("2C", "aload_2");
		
		codeMap.put("10", "bipush");
		codeMap.put("15", "iload");
		codeMap.put("1A", "iload_0");
		codeMap.put("1B", "iload_1");
		codeMap.put("1C", "iload_2");
		codeMap.put("1D", "iload_3");
		
		codeMap.put("25", "fload_3");
		
		codeMap.put("1E", "lload_0");
		
		codeMap.put("24", "fload_2");
		codeMap.put("4C", "astore_1");
		
		codeMap.put("A2", "if_icmp_ge");
		codeMap.put("A4", "if_icmple");
		
		codeMap.put("A7", "goto");
		
		codeMap.put("B1", "return");
		codeMap.put("AC", "ireturn");
		codeMap.put("AE", "freturn");
		
		codeMap.put("03", "iconst_0");
		codeMap.put("04", "iconst_1");
		
		codeMap.put("3C", "istore_1");
		codeMap.put("3D", "istore_2");
		
		codeMap.put("59", "dup");
		
		codeMap.put("60", "iadd");
		codeMap.put("84", "iinc");
		
		codeMap.put("12", "ldc");
	}


	protected ByteCodeCommand(ClassFile clzFile, String opCode){
		this.clzFile = clzFile;
		this.opCode = opCode;
	}
	
	protected ClassFile getClassFile() {
		return clzFile;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	protected ConstantInfo getConstantInfo(int index){
		return getConstantPool().getConstantInfo(index);
	}
	
	protected ConstantPool getConstantPool(){
		return this.getClassFile().getConstantPool();
	}

	public String getOpCode() {
		return opCode;
	}

	public abstract int getLength();  // 以后goto语句要用到

    @Override
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.opCode);
		
		return buffer.toString();
	}

	public abstract String toString(ConstantPool pool);
	
	public String getReadableCodeText(){
		String txt = codeMap.get(opCode);
		if(txt == null){
			throw new RuntimeException("codeMap don't contain: " + opCode);
		}
		return txt;
	}
	
	//public abstract void execute(StackFrame frame,FrameResult result);
}
