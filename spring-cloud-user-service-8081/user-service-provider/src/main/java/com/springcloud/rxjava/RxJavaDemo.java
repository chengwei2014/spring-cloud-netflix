package com.springcloud.rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func0;

/**
 * RxJavaDemo
 * ReactiveX Java  响应式编程框架(android）
 * Java stream() java8
 * 2020-07-22
 */
public class RxJavaDemo {
    public static void main(String[] args) {
        /*
        观察者模式思想
         */
        String[] datas = new String[]{"登录","进入课堂","上课","答疑"};

        Action0 onComplated = new Action0() {
            @Override
            public void call() {
                System.out.println("on Complated");
            }
        };

        //被观察者
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Observable<String> observable1 = Observable.from(datas);
                return observable1.doOnCompleted(onComplated);
            }
        });

        //观察者
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("Observer:onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer:onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("Observer,onNext:" + o);
            }
        };

        //观察者和被观察者建立订阅关系
        observable.subscribe(observer);
//        observable.toBlocking().toFuture();//建立订阅关系
    }
}
