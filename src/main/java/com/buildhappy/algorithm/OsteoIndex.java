package com.buildhappy.algorithm;

import Jama.Matrix;

import java.util.Arrays;


/**
 * Created by caijianfu02 on 16/11/6.
 */
public class OsteoIndex {

    //Ass参考频谱refSpectrum
    //person0,Ass,index_s,Age,Sex,Data_u,Data_sd

    private static double fs = 8 * java.lang.Math.pow(10 ,6);
    private static double t = 1/fs;
    private static int cs=1648;
    private static double d = 0.068;
    private static int N=1024;
    private static Matrix fMatrix;
    private static int personNum = 40;

    static {
        fMatrix = new Matrix(1, N);
        for(int i = 0; i < N; i++){
            fMatrix.set(0, i , i * fs / N);
        }
    }

    /**
     * 根据超声波形计算待测者根骨的超声声速,超声衰减,骨质指数以及T值Z值
     * @param personData 待测者超声波形,共40组,矩阵类型40*1024
     * @param refSpectrum 参考波形,矩阵类型1*1024
     * @param indexS 参考波形的声速标记点(首峰)
     * @param sex 性别 1:男 2:女
     * @param averageData 骨质指数随年龄变化矩阵 矩阵大小81*2
     * @param stdData 骨质指数标准差随年龄变化矩阵 矩阵大小81*2
     * @return
     */
    public static Result dhySti(Matrix personData,Matrix refSpectrum, int indexS,int sex,
                                Matrix averageData, Matrix stdData){
        for(int i = 0; i < personNum; i++){
            double[] onePersonData = personData.getArray()[i];
            int columnNum = onePersonData.length;
            double rowMedian = calArrayMedian(onePersonData);
            double sum = 0;
            int  sumCounter = 0;
            for(int t = 0; t < columnNum; t++){
                if(onePersonData[i] > rowMedian){
                    sum += onePersonData[i];
                    sumCounter++;
                }
            }
            //求峰的阈值
            double minHight = sum / sumCounter;

        }
        return null;
    }

    /**
     * 计算行向量的中值
     * @param numArray
     * @return
     */
    public static double calArrayMedian(double[] numArray){
        Arrays.sort(numArray);
        double median;
        if (numArray.length % 2 == 0)
            median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
        else
            median = (double) numArray[numArray.length/2];
        return median;
    }

    //差分
    public static double[] doubleDataDiff(double[] data){
        if(data == null || data.length <= 1){
            return null;
        }
        int length = data.length;
        double[] diffData = new double[length - 1];
        for(int i = 0; i < length - 1; i++){
            diffData[i] = data[i + 1] - data[i];
        }
        return diffData;
    }

    //差分
    public static int[] intDataDiff(int[] data){
        if(data == null || data.length <= 1){
            return null;
        }
        int length = data.length;
        int[] diffData = new int[length - 1];
        for(int i = 0; i < length - 1; i++){
            diffData[i] = data[i + 1] - data[i];
        }
        return diffData;
    }

    public static int[] sign(double[] data){
        if(data == null || data.length <= 0){
            return null;
        }
        int length = data.length;
        int[] signData = new int[length];
        for(int i = 0; i < length; i++){
            if(data[i] > 0){
                signData[i] = 1;
            }else if(data[i] == 0){
                signData[i] = 1;
            }else {
                signData[i] = -1;
            }
        }
        return signData;
    }

    public static void println(Object o){
        System.out.println(o);
    }

    public static void print(Object o){
        System.out.print(o);
    }

    public static void printMatrix(Object[][] data,int m, int n){
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++ ){
                print(data[i][j] + ",");
            }
            println("\n");
        }
    }
    public static void printArray(int[] data){
        int n = data.length;
        for(int j = 0; j < n; j++ ){
            print(data[j] + ",");
        }
    }

    /**
     * 查找第一个峰值,没有的话返回-1
     * @param data
     * @return
     */
    public static int findFirstPeak(int[] data){
        if(data == null || data.length <= 0){
            return -1;
        }
        for(int i = 0; i < data.length; i++){
            if(data[i] < 0){
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        double[] data = new double[]{1.1, 2.5, 3.3, 3.3, 3.3 ,2.3};
        int[] signData = intDataDiff(sign(doubleDataDiff(data)));
        printArray(signData);
        println(findFirstPeak(signData));

    }
}
