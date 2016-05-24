package com.buildhappy.sortformap;

import java.util.*;

/**
 * Created by caijianfu02 on 16/5/4.
 */
public class SortForMap {
    public static void main(String[] args){
        Map<String , Integer> data = new HashMap<String, Integer>();
        data.put("k1" , 11);
        data.put("k3" , 13);
        data.put("k5" , 15);
        data.put("k2" , 12);
        data.put("k4" , 14);
        println(data);
        println(sortMapByValue(data));
    }
    public static Map<String , Integer> sortMapByValue(Map<String , Integer> srcMap){
        Map<String , Integer> sortedMap = new LinkedHashMap<String, Integer>();
        //将Map的entry保存到List中
        List<Map.Entry<String , Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(srcMap.entrySet());
        //根据定义的比较器进行排序
        Collections.sort(entryList , new MapValueComparator());
        //遍历将List中的Entry保存到SortedMap中
        Iterator<Map.Entry<String , Integer>> iteratorForEntryList = entryList.iterator();
        Map.Entry<String , Integer> curEntry;
        while(iteratorForEntryList.hasNext()){
            curEntry = iteratorForEntryList.next();
            sortedMap.put(curEntry.getKey() , curEntry.getValue());
        }
        return sortedMap;

    }
    private static class MapValueComparator implements Comparator<Map.Entry<String , Integer>> {
        public int compare(Map.Entry<String, Integer> entry1 , Map.Entry<String, Integer> entry2) {
            return entry1.getValue().compareTo(entry2.getValue());
        }
    }

    public static void println(Object o){
        System.out.println(o);
    }
}
