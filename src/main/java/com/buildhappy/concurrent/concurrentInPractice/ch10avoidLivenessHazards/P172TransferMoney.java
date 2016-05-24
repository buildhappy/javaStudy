package com.buildhappy.concurrent.concurrentInPractice.ch10avoidLivenessHazards;
/**
 * 动态的锁顺序死锁
 * @author buildhappy
 *
 */
public class P172TransferMoney {
	public static final Object tieLock = new Object();
	public static void main(String[] args){
		
	}
	
	//当两个线程同时调用该函数时可能发生死锁，线程1从用户1向用户2转钱，线程2从用户2向用户1转钱。
	public static void transferMoney(final Account fromAct , final Account toAct , final int dollarAmount){
		class Helper{
			public void transfer(){
				if(fromAct.getBalance() < dollarAmount){
					System.out.println("not enough money");
				}else{
					fromAct.debit(dollarAmount);
					toAct.credit(dollarAmount);
				}
			}
		}
		int fromHash = System.identityHashCode(fromAct);
		int toHash = System.identityHashCode(toAct);
		if(fromHash > toHash){
			synchronized(fromAct){
				synchronized(toAct){
					new Helper().transfer();
				}
			}
		}else if(fromHash < toHash){
			synchronized(toAct){
				synchronized(fromAct){
					new Helper().transfer();
				}
			}
		}else{
			synchronized(tieLock){
				synchronized(fromAct){
					synchronized(toAct){
						new Helper().transfer();
					}
				}
			}
		}
	}
	
	class Account{
		int balance;

		public void debit(int amount){
			balance -= amount;
		}
		public void credit(int amount){
			balance += amount;
		}
		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}
		
		
	}
}

