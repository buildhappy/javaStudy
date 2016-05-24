package com.buildhappy.concurrent.concurrentInPractice.concurrentTools;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Test CopyOnWriteArrayList
 * @author buildhappy
 *
 */
public class CopyOnWriteArrayList_Test {

	public static void main(String[] args) {
		/**
		 * test CopyOnWriteArrayList
		 * 是线程安全的，每次在调用set和add方法后，底层数组都会进行一次复制
		 * 当遍历操作大于修改操作时，比较有用
		 * 在不能或不想进行同步遍历，但又需要从并发线程中排除冲突时，它也很有用
		 * 注意:不能在迭代器上进行add、set和remove操作
		 */
		CopyOnWriteArrayList<String> copyList = new CopyOnWriteArrayList<String>();
		copyList.add("fafa");
		copyList.add("fafa3");//调用Arrays.copyOf(elements, len + 1);
		copyList.get(1);//直接返回结果
		
		/**
		 * test CopyOnWriteArraySet
		 * 内部封装了CopyOnWriteArrayList，所有的操作都是在CopyOnWriteArrayList上进行的
		 */
		CopyOnWriteArraySet<String> copySet = new CopyOnWriteArraySet<String>();
		//Collections.syn
		Vector<String> vector = new Vector<String>();
		vector.add("fafa");
		vector.get(0);
		vector.size();
	}

}
