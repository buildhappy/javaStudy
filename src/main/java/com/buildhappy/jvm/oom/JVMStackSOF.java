package com.buildhappy.jvm.oom;
/**
 * 本地线程容量不足导致的栈溢出《java虚拟机》P53
 * 运行参数：
 * -Xss128k(每个线程栈大小)
 * @author buildhappy
 *
 */
public class JVMStackSOF {
	private int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args) throws Throwable {
		JVMStackSOF oom = new JVMStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("Stack length:" + oom.stackLength);
			throw e; 
		}
	}

}
