package com.alex;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public final class Sample {
    public static void main(String[] args) {
        final List<String> symbols = Arrays.asList("GOOG", "AMZN", "INTC", "MSFT");
        final Observable<StockInfo> feed = StockServer.getFeed(symbols);
        System.out.println("Got observable");


        feed.subscribe(new Observer<StockInfo>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(StockInfo stockInfo) {
                if (stockInfo.getValue() < 40) {
                    System.out.println("unsubscribing");
                    disposable.dispose();
                }
                System.out.println(Thread.currentThread().getName() + "   " + stockInfo);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("DONE");
            }
        });

    }
}
