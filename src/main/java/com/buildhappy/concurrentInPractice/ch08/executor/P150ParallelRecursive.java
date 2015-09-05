package com.buildhappy.concurrentInPractice.ch08.executor;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 串行递归转换成并行递归（前提是迭代的操作时独立的）
 * @author buildhappy
 *
 */
public class P150ParallelRecursive {
	//串行迭代
	public<T> void sequentialRecursive(List<Node<T>> nodes , Collection<T> results){
		for(Node<T> node : nodes){
			results.add(node.compute());
			sequentialRecursive(node.getChildren() , results);
		}
	}
	//并行迭代
	//遍历过程仍是串行的，只有compute调用才是并行的
	public<T> void parallelRecursive(final Executor exec , List<Node<T>> nodes , final Collection<T> results){
		for(final Node<T> node : nodes){
			exec.execute(new Runnable(){
				public void run(){
					results.add(node.compute());
				}
			});
			parallelRecursive(exec , node.getChildren() , results);
		}
	}
	//并行迭代的使用
	public<T> Collection<T> getParalelResults(List<Node<T>> nodes) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Queue<T> resultsQueue = new LinkedBlockingQueue<T>();
		parallelRecursive(exec , nodes , resultsQueue);
		exec.shutdown();
		exec.awaitTermination(1000, TimeUnit.MILLISECONDS);
		return resultsQueue;
	}

}

class Node<T>{
	T data;
	List<Node<T>> children;
	List<Node<T>> getChildren(){
		return children;
	}
	//耗时的计算
	T compute(){
		return null;
	}
}
