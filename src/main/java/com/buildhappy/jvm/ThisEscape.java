package com.buildhappy.jvm;

class ThisEscape {

	private int thisCanBeEscape = 0;

	public ThisEscape() {
		System.out.println("before");
		new InnerClass();
	}

	private class InnerClass {
		public InnerClass() {

			// 这里可以在Escape对象完成构造前提前引用到Escape的private变量
			System.out.println(ThisEscape.this.thisCanBeEscape);
		}
	}

	public static void main(String[] args) {

		new ThisEscape();

	}

}