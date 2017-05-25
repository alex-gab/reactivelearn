package com.alex;

import io.reactivex.Observable;

import java.util.Arrays;

public final class SubscriptionAllInOneThreadExample {
    public static void main(String[] args) {
        final Observable<Integer> observable = Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        System.out.println("starting subtask " + Thread.currentThread().getName());

        observable.subscribe(
                i -> {
                    System.out.println("onNext thread enter: " + Thread.currentThread().getName());
                    System.out.println(i);
                    System.out.println("onNext thread exit: " + Thread.currentThread().getName());
                },
                Throwable::printStackTrace,
                () -> System.out.println("onCompleted")
        );

        System.out.println("continuing execution " + Thread.currentThread().getName());
    }
}