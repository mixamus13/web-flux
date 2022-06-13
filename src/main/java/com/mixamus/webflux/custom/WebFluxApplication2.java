package com.mixamus.webflux.custom;

import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WebFluxApplication2 {

    public static void main(String... args) {

        List<Integer> elements = new ArrayList<>();
        Flux.just(34, 56, 7, 90, 2, 5, 3, 4)
                //.log()
                .parallel(2)
                .subscribe(elements::add);
        elements.forEach(System.out::println);
    }

}


class WebFluxApplication3 {
    public static void main(String... args) {
        Flux.just("1,2,3", "4,5,6")
                .flatMap(s -> Flux.fromIterable(Arrays.asList(s.split(","))))
                .log()
                .collect(Collectors.toList())
                .subscribe(System.out::println);
    }
}

class Ser {
    public static void main(String... args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");

        Flux.fromIterable(list)
                .flatMap(s -> Flux.just(s + "x"))
                .collect(Collectors.toList())
                .subscribe(System.out::println);
    }
}

class Heref {
    public static void main(String... args) {
        Flux.just(1, 2, 3, 4, 5)
                .collectMap(key -> "key: " + key, value -> "value: " + value)
                .elapsed()
                .subscribe(System.out::println);
    }
}

@Value
class People {
    String name;
    Integer age;
    Sex sex;

    enum Sex {
        MAN, WOMEN

    }
}

class TestWork2 {
    public static void main(String[] args) {

        Collection<People> peoples = Arrays.asList(
                new People("Vasya", 16, People.Sex.MAN),
                new People("Petr", 23, People.Sex.MAN),
                new People("Elena", 42, People.Sex.WOMEN),
                new People("Ivan", 69, People.Sex.MAN)
        );

        Flux.fromIterable(peoples)
                .filter(s -> s.getSex() == People.Sex.MAN)
                .map(People::getAge)
                .reduce(Integer::max)
                .subscribe(System.out::println);

    }
}
