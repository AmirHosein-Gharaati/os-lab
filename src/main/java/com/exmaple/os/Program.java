package com.exmaple.os;

import java.util.function.Function;

public class Program {

    public Program() {
    }

    public void run(Double value) {
        System.out.println("processing value " + value);

        Function<Double, Double> pipeline = square
                .andThen(multiplyBy2)
                .andThen(subtractBy10);

        Double result = pipeline.apply(value);

        System.out.println("final result is " + result);
    }

    private static final Function<Double, Double> square = x -> x * x;
    private static final Function<Double, Double> multiplyBy2 = x -> x * 2;
    private static final Function<Double, Double> subtractBy10 = x -> x - 10.0;
}
