package com.buildhappy.jvm;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    Timer timer;
    public TimerTest(int time){
        timer = new Timer();
        timer.schedule(new TimerTaskTest01(), time * 1000 , 2000);
    }
    
    public static void main(String[] args) {
        System.out.println("time begin....");
        new TimerTest(3);
    }
}

class TimerTaskTest01 extends TimerTask{

    public void run() {
        System.out.println("Time's up!!!!");
    }
}