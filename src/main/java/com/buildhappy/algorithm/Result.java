package com.buildhappy.algorithm;

/**
 * Created by caijianfu02 on 16/11/6.
 */
public class Result {
    /**
     * 超声衰减
     */
    private float bua;
    /**
     * 超声声速
     */
    private float sos;
    /**
     * 骨质指数
     */
    private float oi;
    /**
     * T值与年轻人比较
     */
    private float tScore;
    /**
     * Z值与同龄人比较
     */
    private float zScore;

    public float getBua() {
        return bua;
    }

    public void setBua(float bua) {
        this.bua = bua;
    }

    public float getSos() {
        return sos;
    }

    public void setSos(float sos) {
        this.sos = sos;
    }

    public float getOi() {
        return oi;
    }

    public void setOi(float oi) {
        this.oi = oi;
    }

    public float gettScore() {
        return tScore;
    }

    public void settScore(float tScore) {
        this.tScore = tScore;
    }

    public float getzScore() {
        return zScore;
    }

    public void setzScore(float zScore) {
        this.zScore = zScore;
    }
}
