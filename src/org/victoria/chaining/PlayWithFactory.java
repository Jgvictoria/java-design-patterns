package org.victoria.chaining;

import org.victoria.chaining.function.Factory;
import org.victoria.chaining.model.Circle;

import java.awt.*;
import java.util.List;

public class PlayWithFactory {

    public static void main(String[] args) {

        Factory<Circle> factory = Factory.createFactory(Circle::new, Color.RED);
        Factory<Circle> factory2 = Factory.createFactory(Circle::new);

        Circle circle = factory.newInstance();
        List<Circle> circles = factory.get5();

        System.out.println("Circle = " + circle);
        System.out.println("List = " + circles);
    }
}
