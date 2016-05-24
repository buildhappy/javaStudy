package com.buildhappy.concurrent.concurrentitcast.cn.itcast.heima2;

/**
 * 线程间的通信
 * 子线程运行10次，接着主线程运行100次，接着又回到子线程运行10次，又主线程运行100次，如此往复50次
 * @author buildhappy
 *
 */
public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		final Business2 business = new Business2();//多线程共用变量是要用final修饰
		new Thread(
				new Runnable() {
					public void run() {
						for(int i=1;i<=50;i++){
							business.sub(i);
						}
					}
				}
		).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
		
	}

}
  class Business2 {
	  private boolean bShouldSub = true;
	  public synchronized void sub(int i){
		  while(!bShouldSub){
			  try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of " + j + ",loop of " + i);
			}
		  bShouldSub = false;
		  this.notify();
	  }
	  
	  public synchronized void main(int i){
		  	while(bShouldSub){
		  		try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  	}
			for(int j=1;j<=100;j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			this.notify();
	  }
  }
