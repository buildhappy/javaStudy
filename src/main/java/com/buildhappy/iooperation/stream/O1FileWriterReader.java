package com.buildhappy.iooperation.stream;
/**
 * 字符流和字节流：
 * <p/>
 * 字节流两个基类：
 * InputStream   OutputStream
 * <p/>
 * <p/>
 * 字符流两个基类：
 * Reader Writer
 * <p/>
 * 先学习一下字符流的特点。
 * <p/>
 * 既然IO流是用于操作数据的，
 * 那么数据的最常见体现形式是：文件。
 * <p/>
 * 那么先以操作文件为主来演示。
 * <p/>
 */

import java.io.*;
public class O1FileWriterReader {

    public static void main(String[] args) throws IOException {
        readFile();
        println("\nreadFileUseArray():");
        readFileUseArray();
    }

    /**
     * 需求:在硬盘上，创建一个文件并写入一些文字数据。
     * 找到一个专门用于操作文件的Writer子类对象。FileWriter。后缀名是父类名.前缀名是该流对象的功能。
     * @throws IOException
     */
    public static void writeDataToNewFile() throws IOException {
        //步骤：
        //1、创建一个FileWriter对象。该对象一被初始化就必须要明确被操作的文件。
        //而且该文件会被创建到指定目录下。如果该目录下已有同名文件，将被覆盖。
        //其实该步就是在明确数据要存放的目的地。
        FileWriter fw = new FileWriter("demo.txt");

        //2、调用write方法，将字符串写入到流中。
        fw.write("abcde");

        //3、刷新流对象中的缓冲中的数据。
        //将数据刷到目的地中。
        //fw.flush();


        //4、关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据。
        //将数据刷到目的地中。
        //和flush区别：flush刷新后，流可以继续使用，close刷新后，会将流关闭。
        fw.close();
    }
    /**
     IO异常的处理方式。
     */
    public static void showExceptionHandle(){
        FileWriter fw = null;//在外面建立引用
        try {
            fw = new FileWriter("demo.txt");//在try内进行初始化
            fw.write("abcdefg");
        } catch (IOException e) {
            throw new RuntimeException("写文件失败");
            //System.out.println("catch:"+e.toString());
        } finally {
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }
    /**
     演示对已有文件的数据续写。
     */
    public static void writeDataToFile() throws IOException {
        //传递一个true参数，代表不覆盖已有的文件。并在已有文件的末尾处进行数据续写。
        FileWriter fw = new FileWriter("demo.txt", true);

        fw.write("nihao\r\nxiexie");//windows中可以用\r或\n进行换行，

        fw.close();
    }

    /**
     * 读取文件内容
     * @throws FileNotFoundException
     */
    public static void readFile() throws IOException {
        //创建一个文件读取流对象，和指定名称的文件相关联。
        //要保证该文件是已经存在的，如果不存在，会发生异常FileNotFoundException
        FileReader fr = new FileReader("demo.txt");

        //调用读取流对象的read方法。
        //read():一次读一个字符。而且会自动往下读,读到末尾返回-1

        int ch = 0;

        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }

		/*
		while(true)
		{
			int ch = fr.read();
			if(ch==-1)
				break;
			System.out.println("ch="+(char)ch);
		}
		*/
        fr.close();
    }

    /**
     * 使用临时数组读文件到内存
     * @throws IOException
     */
    public static void readFileUseArray() throws IOException{
        FileReader reader = new FileReader("demo.txt");
        char[] chars = new char[1024];
        int n;
        while((n = reader.read(chars)) != -1){
            println(new String(chars, 0, n));
        }
        reader.close();
    }
    /**
     需求：将C盘一个文本文件复制到D盘

     复制的原理：
     其实就是将C盘下的文件数据存储到D盘的一个文件中。

     步骤：
     1，在D盘创建一个文件。用于存储C盘文件中的数据。
     2，定义读取流和C盘文件关联。
     3，通过不断的读写完成数据存储。
     4，关闭资源。
     */
    public static void copyFile(){
        FileReader reader = null;
        FileWriter writer = null;
        try{
            reader = new FileReader("demo.txt");
            writer = new FileWriter("demo_copy.txt");
            char[] chars = new char[1024];
            int len;
            while((len = reader.read(chars)) != -1){
                writer.write(chars , 0 , len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(writer != null){
                try{
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void copyFile2() throws IOException {
        FileReader reader = new FileReader("demo.txt");
        FileWriter writer = new FileWriter("demo_copy.txt");
        int ch;
        while((ch = reader.read()) != -1){
            writer.write(ch);
        }
        reader.close();
        writer.close();
    }

    public static void println(String s){
        System.out.println(s);
    }
}
