package com.buildhappy.jdk5.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.win32.StdCallLibrary;

public class HelloWorld {
	
	public interface CLibrary extends StdCallLibrary{ //{Library{
		
//		CLibrary INSTANCE = (CLibrary)Native.loadLibrary(Platform.isWindows()?"msvcrt":"c", CLibrary.class);
//		CLibrary INSTANCE = (CLibrary)Native.loadLibrary(Platform.isWindows()?"msvcrt":"c", CLibrary.class);
//		void printf(String format , Object...args);
		
		CLibrary INSTANCE = (CLibrary)Native.loadLibrary("msvcrt222", CLibrary.class);
		//public int fun(int a , int b);
		void printf(String format, Object... args);
		int add(int a, int b);

	}
	
	interface W32DLL extends Library {
	    int add(int a, int b);
	    int DLLfun2(int a);
	}

	public static void main(String[] args) {
		args = new String[]{"hello" , "world"};
		NativeLibrary.addSearchPath("W32DLL", "D:/develop/Net_space/W32DLL/Debug");
		W32DLL lib = (W32DLL) Native.loadLibrary("W32DLL222", W32DLL.class);
		
		CLibrary.INSTANCE.printf("hello world");
		//System.out.println(CLibrary.INSTANCE.add(1, 2));
//		for(int i = 0; i < args.length; i++){
//			CLibrary.INSTANCE.printf("Argumetn %d:%s \n",i ,  args[i]);
//		}
		
		//System.out.println(CLibrary.INSTANCE.fun(1, 2));
	}

}
