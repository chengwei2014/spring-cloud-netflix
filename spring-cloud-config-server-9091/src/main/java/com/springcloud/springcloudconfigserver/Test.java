package com.springcloud.springcloudconfigserver;

import java.io.*;
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

        BufferedInputStream inputStream = null;
        try {
            File file = new File("E:/test.txt");
            inputStream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * try-catch-finally 中，如果 catch 中 return 了，finally 还会执行验证
     * */
    public static int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {//运行时异常
            a = 30;
            return a;
        } finally {
            a = 40;
            //如果这样，就又重新形成了一条返回路径，由于只能通过1个return返回，所以这里直接返回40
            return a;
        }

    }

}
