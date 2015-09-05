package com.buildhappy.callbackTest;

public class FooBar {
    //组合聚合原则
    private ICallBack callBack;

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
        doSth();
    }

    public void doSth() {
        callBack.postExec();
    }
}