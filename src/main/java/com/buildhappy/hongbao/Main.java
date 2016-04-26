package com.buildhappy.hongbao;

/**
 * Created by fuxuemingzhu on 2015/11/26.
 */
public class Main {
	static int paketsNum;
	static int money;

	HashMap<Integer, Float> mark = new HashMap<Integer, Float>();

	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
		paketsNum = 10;
		money = 5;
		hb(money, paketsNum, 0.01);
	}

	static void hb(double total, int num, double min) {
		for (int i = 1; i < num; i++) {
			double safe_total = (total - (num - i) * min) / (num - i);
			double money = Math.random() * (safe_total - min) + min;
			BigDecimal money_bd = new BigDecimal(money);
			money = money_bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			total = total - money;
			BigDecimal total_bd = new BigDecimal(total);
			total = total_bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("第" + i + "个红包：" + money + ",余额为:" + total + "元");
		}
		System.out.println("第" + num + "个红包：" + total + ",余额为:0元");
	}


	static void zb() {
		for (int a = 0; a <= 10000; a++) {
			if (a % 1000 == 0)
				System.out.println(a);
		}
	}

}