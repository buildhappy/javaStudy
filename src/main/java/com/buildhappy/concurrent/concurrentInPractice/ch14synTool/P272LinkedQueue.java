package com.buildhappy.concurrent.concurrentInPractice.ch14synTool;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 用AtomicReference实现非阻塞队列的插入
 * @author buildhappy
 *
 */
public class P272LinkedQueue<E> {
	public static class Node<E>{
		private E item;
		private AtomicReference<Node<E>> next;
		
		public Node(E item , Node<E> next){
			this.item = item;
			this.next = new AtomicReference<Node<E>>(next);
		}
	}
	private final Node<E> dummy = new Node<E>(null , null);
	private AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
	private AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);
	
	public boolean put(E item){
		Node<E> newNode = new Node<E>(item , null);
		while(true){
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			while(curTail == tail.get()){
				//中间状态,将tail指针向后移动
				if(curTail.next != null){
					tail.compareAndSet(curTail, tailNext);
				}else{
					//处于稳定状态，尝试插入新节点
					if(curTail.next.compareAndSet(null, newNode)){
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}
	
}
