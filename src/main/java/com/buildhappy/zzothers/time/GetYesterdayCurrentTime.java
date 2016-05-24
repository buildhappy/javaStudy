package com.buildhappy.zzothers.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 获取昨天的当前时刻
 * @author Administrator
 *
 */
public class GetYesterdayCurrentTime {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(yesterday);
	}

}
