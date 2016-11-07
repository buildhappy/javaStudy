package com.buildhappy.test;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by caijianfu02 on 16/6/10.
 */
public class Sort {
    private final static Logger log = LoggerFactory
            .getLogger(Sort.class);

    public static List<Integer> reRank(int offset, int limit, List<Integer> srcIds,
                                List<Integer> refIds) {

        log.debug("srcIds: [{}]", srcIds.toString());
        log.debug("refIds: [{}]", refIds.toString());
        Set<Integer> srcSet = Sets.newHashSet();

        if(offset < 0) offset = 0;
        if(limit < 0) limit = Integer.MAX_VALUE;
        //将srcIds拷贝到srcSet并删除重复元素
        for(int i=offset; i<srcIds.size(); i++) {
            srcSet.add(srcIds.get(i));
        }
        println(srcSet);

        Set<Integer> resultSet = Sets.newHashSet();

        int srcSetSize = srcSet.size();
        int targetLen = srcSetSize < limit ? srcSetSize : limit;

        int i = 0;
        Iterator<Integer> refIdIt = refIds.iterator();
        //先遍历一遍refIds,删除其中不包含在srcIds中的元素
        while(refIdIt.hasNext()) {
            Integer refId = refIdIt.next();
            if(srcSet.contains(refId)) {
                resultSet.add(refId);
                i++;
                if(i >= targetLen) {
                    log.debug("rerank ids: [{}]", refIds.subList(0, i).toString());
                    return refIds.subList(0, i);
                }
                continue;
            } else {
                refIdIt.remove();
            }
        }
        println(refIds);
        //将包含在srcIds但是不包含在refIds中的元素添加到refIds中
        for(int j=offset; j<srcIds.size(); j++) {
            if(resultSet.contains(srcIds.get(j))) {
                continue;
            } else {
                refIds.add(srcIds.get(j));
                resultSet.add(srcIds.get(j));
                i++;
                if(i >= targetLen) {
                    log.debug("rerank ids: [{}]", refIds.subList(0, i).toString());
                    return refIds;
                }
            }
        }

        log.debug("rerank ids:[{}]", refIds.subList(0, i).toString());
        return refIds;
    }

    public static void main(String[] args){
        List<Integer> srcIds = new LinkedList<Integer>();
        srcIds.add(1);
        srcIds.add(1);
        srcIds.add(5);
        srcIds.add(7);
        srcIds.add(3);
        List<Integer> refIds = new LinkedList<Integer>();
        refIds.add(1);
        refIds.add(3);
        srcIds.retainAll(srcIds);
        println(srcIds);
        //println(reRank(0 , srcIds.size() , srcIds , refIds));
    }

    public static void println(Object o){
        System.out.println(o);
    }
}
