package com.buildhappy.iooperation.stream;

import java.io.*;

public class O2BufferedWriterReader {
    public static void main(String[] args) throws IOException {
        println("demoBufferedWriter()");
        demoBufferedWriter();
        println("showMyBufferedReader()");
        showMyBufferedReader();
    }

    /**
     * 演示BufferedWriter
     * 缓冲区的出现是为了提高流的操作效率而出现的。
     * 所以在创建缓冲区之前，必须要先有流对象。
     * <p/>
     * 该缓冲区中提供了一个跨平台的换行符 newLine();
     *
     * @throws IOException
     */
    public static void demoBufferedWriter() throws IOException {
        //创建一个字符写入流对象。
        FileWriter fw = new FileWriter("demo.txt");

        //为了提高字符写入流效率。加入了缓冲技术。
        //只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可。
        BufferedWriter bufw = new BufferedWriter(fw);

        for (int x = 1; x < 5; x++) {
            bufw.write("abcd" + x);
            //写行分隔符
            bufw.newLine();
            bufw.flush();
        }

        //记住，只要用到缓冲区，就要记得刷新。
        //bufw.flush();

        //其实关闭缓冲区，就是在关闭缓冲区中的流对象。
        bufw.close();
    }

    /**
     * 字符读取流缓冲区：
     * 该缓冲区提供了一个一次读一行的方法 readLine，方便于对文本数据的获取。
     * 当返回null时，表示读到文件末尾。
     * <p/>
     * readLine方法返回的时候只返回回车符之前的数据内容。并不返回回车符。
     */
    public static void demoFileBufferedReader() throws IOException {
        //创建一个读取流对象和文件相关联。
        FileReader fr = new FileReader("demo.txt");

        //为了提高效率。加入缓冲技术。将字符读取流对象作为参数传递给缓冲对象的构造函数。
        BufferedReader bufr = new BufferedReader(fr);

        String line = null;

        while ((line = bufr.readLine()) != null) {
            System.out.print(line);
        }

        bufr.close();
    }

    /**
     * 字符读取流缓冲区：
     * 该缓冲区提供了一个一次读一行的方法 readLine，方便于对文本数据的获取。
     * 当返回null时，表示读到文件末尾。
     * readLine方法返回的时候只返回回车符之前的数据内容。并不返回回车符。
     */
    public static void copyFileByBuffer()throws IOException {
        //创建一个读取流对象和文件相关联。
        FileReader fr = new FileReader("buf.txt");

        //为了提高效率。加入缓冲技术。将字符读取流对象作为参数传递给缓冲对象的构造函数。
        BufferedReader bufr = new BufferedReader(fr);

        String line = null;

        while ((line = bufr.readLine()) != null) {
            System.out.print(line);
        }

        bufr.close();
    }

    /**
     * 自己定义BufferedReader
     * @throws IOException
     */
    public static void showMyBufferedReader()throws IOException {
        FileReader fr = new FileReader("demo.txt");
        MyBufferedReader myBuf = new MyBufferedReader(fr);
        String line = null;

        while ((line = myBuf.myReadLine()) != null) {
            System.out.println(line);
        }

        myBuf.myClose();
    }
    /**
     * 明白了BufferedReader类中特有方法readLine的原理后，
     * 可以自定义一个类中包含一个功能和readLine一致的方法。
     * 来模拟一下BufferedReader
     */
    static class MyBufferedReader extends Reader {
        private Reader r;
        MyBufferedReader(Reader r) {
            this.r = r;
        }
        //可以一次读一行数据的方法,去除空格
        public String myReadLine() throws IOException {
            //定义一个临时容器。原BufferReader封装的是字符数组。
            //为了演示方便。定义一个StringBuilder容器。因为最终还是要将数据变成字符串。
            StringBuilder sb = new StringBuilder();
            int ch = 0;
            while ((ch = r.read()) != -1) {
                if (ch == '\r')
                    continue;
                if (ch == '\n')//注意最后一行没有/n
                    return sb.toString();
                else
                    sb.append((char) ch);
            }
            //最后一行没有/n，所以要进行处理
            if (sb.length() != 0)
                return sb.toString();
            return null;
        }
        /*
        覆盖Reader类中的抽象方法
        */
        public int read(char[] cbuf, int off, int len) throws IOException {
            return r.read(cbuf, off, len);
        }

        public void close() throws IOException {
            r.close();
        }

        public void myClose() throws IOException {
            r.close();
        }
    }

    public static void println(String s) {
        System.out.println(s);
    }
}
