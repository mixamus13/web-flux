package com.mixamus.webflux.custom;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String... args) {
//        Flux<String> flux1 = Flux.just("max", "foo", "bar");
//        Flux<String> flux2 = Flux.fromIterable(Arrays.asList("A", "B", "C"));
//        Flux<Integer> flux3 = Flux.range(3, 5);
//
//        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .take(8)
//                .skip(3)
//                .subscribe(value -> System.out.println("Value: " + value));

        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {

                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
