package com.buildhappy.iooperation.stream;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by caijianfu02 on 16/4/28.
 *
 打印流：
 该流提供了打印方法，可以将各种数据类型的数据都原样打印。

 字节打印流：
 PrintStream
 构造函数可以接收的参数类型：
 1，file对象。File
 2，字符串路径。String
 3，字节输出流。OutputStream



 字符打印流：
 PrintWriter
 构造函数可以接收的参数类型：
 1，file对象。File
 2，字符串路径。String
 3，字节输出流。OutputStream
 4，字符输出流，Writer。


 */
public class O5PrintStream {
    public static void main(String[] args){
        
    }
    
    public static void printSystemInfo()throws IOException{
        Properties prop = System.getProperties();

        //System.out.println(prop);
        prop.list(new PrintStream("sysinfo.txt"));
    }
    
    
    public static void printLog(){
        try {
            int[] arr = new int[2];
            System.out.println(arr[3]);
        } catch (Exception e) {

            try {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s = sdf.format(d);

                PrintStream ps = new PrintStream("exeception.log");
                ps.println(s);
                System.setOut(ps);


            } catch (IOException ex) {
                throw new RuntimeException("日志文件创建失败");
            }
            e.printStackTrace(System.out);
        }
    }

    /**
     * 将控制台的信息保存到文件中
     * @throws IOException
     */
    public static void saveScreamToFile() throws IOException {
        BufferedReader bufr =
                new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new FileWriter("a.txt"), true);
        String line = null;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line))
                break;
            out.println(line.toUpperCase());
        }
        out.close();
        bufr.close();
    }
}
