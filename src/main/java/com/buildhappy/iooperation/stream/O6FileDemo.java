package com.buildhappy.iooperation.stream;

import java.io.*;
import java.util.*;
/*
File类常见方法：
1，创建。
	boolean createNewFile():在指定位置创建文件，如果该文件已经存在，则不创建，返回false。
						和输出流不一样，输出流对象一建立创建文件。而且文件已经存在，会覆盖。

	boolean mkdir():创建文件夹。
	boolean mkdirs():创建多级文件夹。
2，删除。
	boolean delete()：删除失败返回false。如果文件正在被使用，则删除不了返回falsel。
	void deleteOnExit();在程序退出时删除指定文件。


3，判断。
	boolean exists() :文件是否存在.
	isFile():
	isDirectory();
	isHidden();
	isAbsolute();

4，获取信息。
	getName():
	getPath():
	getParent():

	getAbsolutePath()
	long lastModified()
	long length()

*/


class O6FileDemo {
    public static void main(String[] args) throws IOException {
        method_5();
    }

    public static void method_5() {
        File f1 = new File("c:\\Test.java");
        File f2 = new File("d:\\hahah.java");

        println("rename:" + f2.renameTo(f1));

    }

    public static void basicOpreation() {
        File f = new File("file.txt");
        println("path:" + f.getPath());
        println("abspath:" + f.getAbsolutePath());
        println("parent:" + f.getParent());//该方法返回的是绝对路径中的父目录。如果获取的是相对路径，返回null。
        //如果相对路径中有上一层目录那么该目录就是返回结果。

        //记住在判断文件对象是否是文件或者目的时，必须要先判断该文件对象封装的内容是否存在。
        //通过exists判断。
        println("dir:" + f.isDirectory());
        println("file:" + f.isFile());
        println("f.isAbsolute():" + f.isAbsolute());
        //创建和删除文件
        //println("create:"+f.createNewFile());
        //println("delete:"+f.delete());

        //创建文件夹
        File dir = new File("abc\\kkk\\a\\a\\dd\\ee\\qq\\aaa");
        //println("mkdir:" + dir.mkdirs());

        println(File.separator);
    }

    /**
     * 统计某目录下的所有文件，包括该目录文件夹里的文件
     * @param dir
     * @param list
     */
    public static void fileToList(File dir, List<File> list) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory())
                fileToList(file, list);
            else {
                //if (file.getName().endsWith(".java"))
                list.add(file);
            }
        }
    }

    /**
     * 使用FilenameFilter，过滤掉不相干的文件
     */
    public static void listDemo_2() {
        File dir = new File("/Users/caijianfu02");

        String[] arr = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".bmp");
            }
        });
        println("len:" + arr.length);
        for (String name : arr) {
            println(name);
        }
    }

    /**
     * 将多个文件顺序读入到一个新的文件中
     * @throws IOException
     */
    public static void readMultFileToNewFile() throws IOException {
        Vector<FileInputStream> v = new Vector<FileInputStream>();

        v.add(new FileInputStream("c:\\1.txt"));
        v.add(new FileInputStream("c:\\2.txt"));
        v.add(new FileInputStream("c:\\3.txt"));

        Enumeration<FileInputStream> en = v.elements();

        SequenceInputStream sis = new SequenceInputStream(en);

        FileOutputStream fos = new FileOutputStream("c:\\4.txt");

        byte[] buf = new byte[1024];

        int len = 0;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }

        fos.close();
        sis.close();
    }

    /**
     * 对文件进行合并
     * @throws IOException
     */
    public static void merge() throws IOException {
        ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();

        for (int x = 1; x <= 3; x++) {
            al.add(new FileInputStream("c:\\splitfiles\\" + x + ".part"));
        }

        final Iterator<FileInputStream> it = al.iterator();

        Enumeration<FileInputStream> en = new Enumeration<FileInputStream>() {
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            public FileInputStream nextElement() {
                return it.next();
            }
        };

        SequenceInputStream sis = new SequenceInputStream(en);

        FileOutputStream fos = new FileOutputStream("c:\\splitfiles\\0.bmp");

        byte[] buf = new byte[1024];

        int len = 0;

        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }

        fos.close();
        sis.close();
    }

    /**
     * 对文件进行分割
     * @throws IOException
     */
    public static void splitFile() throws IOException {
        FileInputStream fis = new FileInputStream("c:\\1.bmp");

        FileOutputStream fos = null;

        byte[] buf = new byte[1024 * 1024];

        int len = 0;
        int count = 1;
        while ((len = fis.read(buf)) != -1) {
            fos = new FileOutputStream("c:\\splitfiles\\" + (count++) + ".part");
            fos.write(buf, 0, len);
            fos.close();
        }

        fis.close();

    }

    public static void println(Object obj) {
        System.out.println(obj);
    }
}
