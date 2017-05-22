package com.alex;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

public final class StockServer {
    public static Observable<StockInfo> getFeed(List<String> symbols) {
        return Observable.create(subscriber -> emit(subscriber, symbols));
    }

    private static void emit(ObservableEmitter<StockInfo> subscriber, List<String> symbols) {
        System.out.println(Thread.currentThread().getName() + " start emitting...");
        symbols.stream().
                map(StockInfo::fetch).
                forEach(subscriber::onNext);
        subscriber.onComplete();
    }


}
