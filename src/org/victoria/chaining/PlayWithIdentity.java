package org.victoria.chaining;

import org.victoria.chaining.function.Function;

public class PlayWithIdentity {

    public static void main(String[] args) {

        Function<String, String> identity = Function.identity();
    }
}
