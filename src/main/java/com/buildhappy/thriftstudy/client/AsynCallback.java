package com.buildhappy.thriftstudy.client;

import java.util.concurrent.CountDownLatch;

import org.apache.thrift.async.AsyncMethodCallback;
import com.buildhappy.thriftstudy.thriftGen.ThriftService.AsyncClient.queryUser_call;
import org.junit.Test;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年11月13日
 */
public class AsynCallback implements AsyncMethodCallback<queryUser_call> {

	private CountDownLatch latch;

	public AsynCallback(CountDownLatch latch){
		this.latch = latch;
	}
	@Test
	public void onComplete(queryUser_call arg0) {
		System.out.println("onComplete");
		try{
			System.out.println("AsynCall result =: " + arg0.getResult().toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			latch.countDown();
		}
	}

	public void onError(Exception arg0) {
		System.out.println("onError: " + arg0.getMessage());
		latch.countDown();
	}
}