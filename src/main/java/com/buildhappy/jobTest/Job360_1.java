package com.buildhappy.jobTest;

import java.util.Scanner;

/**
 * 输入：
 *  3
 *  80，80，100，120
 *  50，90，100，110
 *  60，70，100，100
 * 输出：
 *  Gongfei
 *  Fail
 *  Zifei
 */
public class Job360_1 {

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        while(n-- != 0){
            int[] data = new int[4];
            data[0] = cin.nextInt();
            data[1] = cin.nextInt();
            data[2] = cin.nextInt();
            data[3] = cin.nextInt();
            judgeClass(data);
        }

    }

    public static void judgeClass(int[] scores){
        int total = scores[0] + scores[1] + scores[2] + scores[3];
        if(scores[0] < 60 || scores[1] < 60 || scores[2] < 90 || scores[3] < 90 || total < 310){
            System.out.println("Fail");
            return;
        }
        if(total >= 350){
            System.out.println("Gongfei");
            return;
        }
        System.out.println("Zifei");
    }
}
