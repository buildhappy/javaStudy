package com.buildhappy.concurrentInPractice.ch03shareObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class P52MonitorVehicleTracker {
	Map<String , MutablePoint> locations;
	public P52MonitorVehicleTracker(Map<String , MutablePoint> locations){
		this.locations = locations;
	}
	public synchronized Map<String , MutablePoint> getLocations(){
		return deepCopy(locations);
	}
	public static Map<String , MutablePoint> deepCopy(Map<String , MutablePoint> m){
		Map<String , MutablePoint> result = new HashMap<String , MutablePoint>();
		for(String key : m.keySet()){
			result.put(key, m.get(key));
		}
		return Collections.unmodifiableMap(result);
	}
	public static void main(String[] args) {
		Map<String , MutablePoint> locations = new HashMap<String , MutablePoint>();
		locations.put("p1", new MutablePoint(1 , 2));
		P52MonitorVehicleTracker tracker = new P52MonitorVehicleTracker(locations);
		Map<String , MutablePoint> getLoc = tracker.getLocations();
		getLoc.remove("p1");//报异常，因为返回的对象不能被改变
	}

}

//非线程安全的类来存放位置坐标
class MutablePoint{
	public int x ,y;
	public MutablePoint(){
		this.x = 0;
		this.y = 0;
	}
	public MutablePoint(int x ,int y){
		this.x = x;
		this.y = y;
	}
}
