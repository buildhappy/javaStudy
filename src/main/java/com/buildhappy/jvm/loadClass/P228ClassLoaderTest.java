package com.buildhappy.jvm.loadClass;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器对instanceof关键字运算的结果的影响
 * 最后返回false，说明此时内存中存在两个P228ClassLoaderTest对象，但是由于两个对象的类加载器不同，
 * 一个对象由系统应用程序类加载器加载的，另一个是由我们自定义的类加载器加载的。
 * Created by buildhappy on 15/9/9.
 */
public class P228ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    //System.out.println(fileName);
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
//                    System.out.println(is.available());
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name , b , 0 , b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.buildhappy.jvm.loadClass.P228ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.buildhappy.jvm.loadClass.P228ClassLoaderTest);
    }
}
