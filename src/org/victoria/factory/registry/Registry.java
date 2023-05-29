package org.victoria.factory.registry;

import org.victoria.chaining.function.Consumer;
import org.victoria.factory.factory.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface Registry<T> {

    Factory<? extends T> buildShapeFactory(String shape);

    static <T> Registry<T> createRegistry(
            Consumer<Builder<T>> consumer, Function<String, Factory<T>> errorFunction) {

        Map<String, Factory<T>> map = new HashMap<>();
        Builder<T> builder = (label, factory) -> map.put(label, factory);
        consumer.accept(builder);

        return shape -> map.computeIfAbsent(shape, errorFunction);
    }
}
