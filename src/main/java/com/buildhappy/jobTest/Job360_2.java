package com.buildhappy.jobTest;

import java.util.Scanner;

/**
 * 输入一个数字，将该数字所有的素数的乘积以火柴棍的形式输出
 * 输入：10
 * 输出：2*5
 */
public class Job360_2 {
    public static void main(String[] args){
//        Scanner cin = new Scanner(System.in);
//        int n = cin.nextInt();
        //start(n);
        printNum(5);
        printNum(5);

    }
    public static void start(int n){
        int[] data = primeFactor(n);
        int len = data.length;
        if(len <= 0) return;
        for(int i = 0; i < len - 1; i++){
            printNum(i);
            System.out.print("*");
        }
        printNum(len - 1);
    }
    public static int[] primeFactor(int num){
        StringBuilder builder = new StringBuilder();
        int primeNum = 2;//最小质数
        while(primeNum <= num) {
            if (primeNum == num) {
                //System.out.println(num);
                builder.append(num);
                break;
            }else if(num % primeNum == 0){
                //System.out.println(primeNum + "*");
                num /= primeNum;
                builder.append(primeNum + ",");

            }else {
                primeNum++;
            }
        }
        String[] datas = builder.toString().split(",");
        int[] data = new int[datas.length];
        for(int i = 0; i < data.length; i++){
            data[i] = Integer.parseInt(datas[i]);
        }
        return data;
    }
    //将数字打印成火柴
    public static void printNum(int n){
        char[][] numChar = new char[5][3];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                numChar[i][j] = ' ';
            }
        }
        switch (n){
            case 1:
                numChar[1][0] = '|';
                numChar[3][0] = '|';
                break;
            case 2:
                numChar[0][1] = '-';
                numChar[2][1] = '-';
                numChar[4][1] = '-';
                numChar[3][0] = '|';
                numChar[1][2] = '|';
                break;
            case 3:
                numChar[0][1] = '-';
                numChar[2][1] = '-';
                numChar[4][1] = '-';
                numChar[3][2] = '|';
                numChar[1][2] = '|';
                break;
            case 4:
                numChar[1][0] = '|';
                numChar[1][2] = '|';
                numChar[3][2] = '|';
                numChar[2][1] = '-';
                break;
            case 5:
                numChar[0][1] = '-';
                numChar[2][1] = '-';
                numChar[4][1] = '-';
                numChar[1][0] = '|';
                numChar[3][2] = '|';
                break;
            case 6:
                numChar[2][1] = '-';
                numChar[4][1] = '-';
                numChar[1][0] = '|';
                numChar[3][0] = '|';
                numChar[3][2] = '|';
                break;
            case 7:
                numChar[0][1] = '-';
                numChar[1][2] = '|';
                numChar[3][2] = '|';
                break;
            case 8:
                numChar[0][1] = '-';
                numChar[2][1] = '-';
                numChar[4][1] = '-';

                numChar[1][0] = '|';
                numChar[3][0] = '|';
                numChar[1][2] = '|';
                numChar[3][2] = '|';
                break;
            case 9:
                numChar[0][1] = '-';
                numChar[2][1] = '-';

                numChar[1][0] = '|';
                numChar[1][2] = '|';
                numChar[3][2] = '|';
                break;


        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(numChar[i][j]);
            }
            System.out.println();
        }

            for(int j = 0; j < 3; j++){
                System.out.print(numChar[4][j]);
            }

    }
}