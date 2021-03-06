package com.coderising.jvm.clz;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFile {
	
	private int minorVersion;
	private int majorVersion;
	
	private AccessFlag accessFlag;
	private ClassIndex clzIndex;
	private ConstantPool pool;
	
	private List<Field> fields;
	private List<Method> methods;
	
	public ClassIndex getClzIndex() {
		return clzIndex;
	}
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}
	
	
	
	public ConstantPool getConstantPool() {		
		return pool;
	}
	public int getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	public int getMajorVersion() {
		return majorVersion;
	}
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public void setConstPool(ConstantPool pool) {
		this.pool = pool;
		
	}
	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;		
	}
	
	
	public void print(){
		
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:"+ getClassName());
		
		System.out.println("Super Class Name:"+ getSuperClassName());
		
		
	}
	
	private String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	private String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
	
	public void setFields(List<Field> fields){
		this.fields = fields;
	}
	
	public void setMethods(List<Method> methods){
		this.methods = methods;
	}
	
	public List<Field> getFields(){
		return fields;
	}
	
	public List<Method> getMethods(){
		return methods;
	}
	
	public String getClassNameVal(int index){
		return pool.getUTF8String(index);
	}
	
	public String getDescVal(int index){
		return pool.getUTF8String(index);
	}
	
	public Method getMainMethod(){
		return getMethod("main", "([Ljava/lang/String;)V");
	}
	
	//getMethod("<init>", "(Ljava/lang/String;I)V")
	public Method getMethod(String methodName, String parameter){
		System.out.println();System.out.println();System.out.println("Entering getMethod: " + methodName + " parameter: " + parameter);
		for(Method m : methods){
			if(getClassNameVal(m.getNameIndex()).equalsIgnoreCase(methodName) &&
			   getDescVal(m.getDescriptorIndex()).equalsIgnoreCase(parameter)){
				return m;
			}
		}
		return null;
	}
}
