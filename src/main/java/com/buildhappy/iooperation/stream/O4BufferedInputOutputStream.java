package com.buildhappy.iooperation.stream;

import java.io.*;

/**
 * 使用读取缓存的作用是，在对文件进行操作时，可以一次性读取多个byte，保存在缓存(内存)中，
 * 程序可以直接从内存中取数据，而不是每取一个byte都操作磁盘
 *
 * 不带缓冲的操作，每读一个字节就要写入一个字节，由于涉及磁盘的IO操作相比内存的操作要慢很多，所以不带缓冲的流效率很低。
 * 带缓冲的流，可以一次读很多字节，但不向磁盘中写入，只是先放到内存里。
 * 等凑够了缓冲区大小的时候一次性写入磁盘，这种方式可以减少磁盘操作次数，速度就会提高很多！这就是两者的区
 *
 * Created by caijianfu02 on 16/4/28.
 */
public class O4BufferedInputOutputStream {

    public static void main(String[] args){

    }

    /**
     * 直接通过InputStream读取
     * @return
     * @throws IOException
     */
    public static long readByInputStream() throws IOException {
        InputStream in=new FileInputStream("demo.txt");
        byte[] b=new byte[8192];
        int l=0;
        long start=System.currentTimeMillis();
        while(in.read(b,0,8192)!=-1){
        }
        long end=System.currentTimeMillis();
        return end-start;
    }

    /**
     * 通过BufferedInputStream读取
     * @return
     * @throws IOException
     */
    public static long readByBufferedInputStream() throws IOException {
        BufferedInputStream in=new BufferedInputStream(new FileInputStream("demo.txt"));
        byte[] b=new byte[8192];
        int l=0;
        long start=System.currentTimeMillis();
        while(in.read(b,0,8192)!=-1){
        }
        long end=System.currentTimeMillis();
        return end-start;
    }

    /**
     * 复制文件
     * @throws IOException
     */
    public static void copy_2() throws IOException {
        MyBufferedInputStream bufis = new MyBufferedInputStream(new FileInputStream("c:\\9.mp3"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("c:\\3.mp3"));

        int by = 0;

        while ((by = bufis.myRead()) != -1) {
            bufos.write(by);
        }

        bufos.close();
        bufis.myClose();
    }
    /**
     * 复制文件
     * @throws IOException
     */
    //通过字节流的缓冲区完成复制。
    public static void copy_1() throws IOException {
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("c:\\0.mp3"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("c:\\1.mp3"));
        int by = 0;
        while ((by = bufis.read()) != -1) {
            bufos.write(by);
        }
        bufos.close();
        bufis.close();
    }

    static class MyBufferedInputStream {
        private InputStream in;

        private byte[] buf = new byte[1024 * 4];

        private int pos = 0, count = 0;

        MyBufferedInputStream(InputStream in) {
            this.in = in;
        }

        //一次读一个字节，从缓冲区(字节数组)获取。
        public int myRead() throws IOException {
            //通过数据流对象读取硬盘上数据，并存储buf中，只在这一次操作硬盘
            if (count == 0) {
                count = in.read(buf);
                if (count < 0)
                    return -1;
                pos = 0;
                byte b = buf[pos];
                count--;
                pos++;
                return b & 255;
            } else if (count > 0) {
                byte b = buf[pos];
                count--;
                pos++;
                return b & 0xff;
            }
            return -1;
        }

        public void myClose() throws IOException {
            in.close();
        }
    }
}



