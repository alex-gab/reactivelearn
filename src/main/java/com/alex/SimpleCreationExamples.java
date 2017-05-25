package com.alex;

import io.reactivex.Observable;

import java.util.Arrays;

public final class SimpleCreationExamples {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.fromArray(21);
        observable.subscribe(System.out::println);

        observable = Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        observable.subscribe(System.out::println);
    }
}
