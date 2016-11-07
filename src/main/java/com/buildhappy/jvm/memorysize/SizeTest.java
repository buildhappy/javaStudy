package com.buildhappy.jvm.memorysize;

public class SizeTest {
    private int x;
    private int y;

    public static void main(String [] args) {
        System.out.println(Runtime.getRuntime().totalMemory());
        //System.out.println(ObjectSizeFetcher.getObjectSize(new SizeTest()));
    }
}