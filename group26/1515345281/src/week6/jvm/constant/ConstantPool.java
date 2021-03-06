package week6.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

	private List<ConstantInfo> constantInfos=new ArrayList<ConstantInfo>();

	public ConstantPool(){};
	
	public ConstantInfo getConstantInfo(int index) {
		return constantInfos.get(index);
	}

	public void addConstantInfo(ConstantInfo info) {
		this.constantInfos.add(info);
	}
	
	public String getUTF8String(int index){
		return ((UTF8Info)this.constantInfos.get(index)).getValue();
	}
	
	public int getSize(){
		return this.constantInfos.size()-1;
	}
}
