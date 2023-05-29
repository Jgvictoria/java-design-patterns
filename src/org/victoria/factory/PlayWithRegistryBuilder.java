package org.victoria.factory;

import org.victoria.chaining.function.Consumer;
import org.victoria.factory.factory.Factory;
import org.victoria.factory.model.Rectangle;
import org.victoria.factory.model.Shape;
import org.victoria.factory.model.Triangle;
import org.victoria.factory.registry.Builder;
import org.victoria.factory.registry.Registry;
import org.victoria.factory.registry.SwitchRegistry;

public class PlayWithRegistryBuilder {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Consumer<Builder<Shape>> consumer1 =
                builder -> builder.register("rectangle", Rectangle::new);
        Consumer<Builder<Shape>> consumer2 =
                builder -> builder.register("triangle", Triangle::new);

        Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2);

        Registry<Shape> registry = Registry.createRegistry(
                initializer, s -> {
                    throw new IllegalArgumentException("Unknown shape: " + s);
                });
        Factory<Rectangle> buildRectangleFactory = (Factory<Rectangle>) registry.buildShapeFactory("rectangle");

        Rectangle rectangle = buildRectangleFactory.newInstance();
        System.out.println("Rectangle = " + rectangle);

        Factory<Triangle> buildTriangleFactory = (Factory<Triangle>) registry.buildShapeFactory("triangle");

        Triangle triangle = buildTriangleFactory.newInstance();
        System.out.println("Triangle = " + triangle);
    }
}
