package com.alex;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public final class ParallelThreadExample {
    public static void main(String[] args) throws InterruptedException {
        final Subscriber[] subscribers = {new MySubscriber(), new MySubscriber(), new MySubscriber(), new MySubscriber()};

        Flowable.range(1, 10).
                parallel().
                filter(i -> i % 2 == 0).
                doOnNext(i -> {
                    System.out.println("parallel thread in: " + Thread.currentThread().getName());
                    System.out.println("parallel: " + i);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("parallel thread out: " + Thread.currentThread().getName());
                }).
                runOn(Schedulers.io()).
                subscribe(subscribers);


    }


}

class MySubscriber implements Subscriber<Integer> {
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe: " + Thread.currentThread().getName());
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext thread enter: " + Thread.currentThread().getName());
        System.out.println(integer);
        System.out.println("onNext thread exit: " + Thread.currentThread().getName());
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("completed: " + Thread.currentThread().getName());
    }

}
