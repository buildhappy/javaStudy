package com.buildhappy.jvm;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 输出2015-01-05 16:56:35格式的时间
 * @author Administrator
 *
 */
public class DateTest {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		String time = sdf.format(date);
		System.out.println(time);
	}

}
