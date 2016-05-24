package com.buildhappy.concurrent.concurrentInPractice.ch14synTool;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用原子变量(AtomicReference)实现非阻塞的线程安全的栈
 * @author buildhappy
 *
 */
public class P271ConcurrentStack<E> {
	private AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

	public void push(E item){
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do{
			oldHead = top.get();
			newHead.next = oldHead;
		}while(top.compareAndSet(oldHead, newHead));//如果在开始插入时，位于栈顶的节点没有变化，那么CAS返回true。
	}
	
	public E pop(){
		Node<E> oldHead;
		Node<E> newHead;
		do{
			oldHead = top.get();
			if(oldHead == null){
				return null;
			}
			newHead = oldHead.next;
			
		}while(top.compareAndSet(oldHead , newHead));
		return oldHead.item;
	}
	
    static class Node<E> {
        public final E item;
        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head.next
         * - null, meaning there is no successor (this is the last node)
         */
        Node<E> next;

        Node(E x) { item = x; }
    }
}
