package com.buildhappy.jvm.oom;
/**
 * 过多创建线程导致虚拟机内存不足《java虚拟机》P55
 * 运行参数：
 * -Xss2M(每个线程栈大小)
 * @author buildhappy
 *
 */
public class JVMStackOOM {
	
	public void dontStop(){
		while(true){
			
		}
	}
	public void stackLeakByThread(){
		while(true){
			new Thread(new Runnable(){
				public void run(){
					dontStop();
				}
			}).start();
		}
	}
	public static void main(String[] args) {
		JVMStackOOM oom = new JVMStackOOM();
		oom.stackLeakByThread();
	}

}
