package com.buildhappy.iooperation.stream;
/**
 * 字符流(已学)：
 * FileReader
 * FileWriter。
 * BufferedReader
 * BufferedWriter
 *
 * 字节流(接下来)：
 * InputStream  OutputStream
 *
 * 需求，想要操作图片数据。这时就要用到字节流。
 * 复制一个图片.
 */

import java.io.*;

public class O3FileInputOutputStream {
    public static void main(String[] args) throws IOException {
        readFile_3();
    }

    public static void readFile_3() throws IOException {
        FileInputStream fis = new FileInputStream("demo.txt");

//		int num = fis.available();
        byte[] buf = new byte[fis.available()];//定义一个刚刚好的缓冲区。不用在循环了。

        fis.read(buf);

        System.out.println(new String(buf));

        fis.close();
    }


    public static void readFile_2() throws IOException {
        FileInputStream fis = new FileInputStream("demo.txt");

        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        fis.close();
    }


    public static void readFile_1() throws IOException {
        FileInputStream fis = new FileInputStream("demo.txt");
        int ch = 0;
        while ((ch = fis.read()) != -1) {
            println((char) ch);
        }
        fis.close();
    }

    public static void writeFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.txt");
        fos.write("abcde".getBytes());
        fos.close();
    }


    public static void println(Object s){
        System.out.println(s);
    }
}


