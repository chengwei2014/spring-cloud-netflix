package com.springcloud.springcloudconfigserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 日常测试
 * 2020-07-15
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println("原始数组：" + list);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next() == 2){
                iterator.remove();
            }
        }
        System.out.println("移除元素后数组：" + list);

        for (Integer i:list){
            if (i == 3){
                list.remove(i);
            }
        }
        System.out.println("二次移除元素后数组：" + list);
    }
}
