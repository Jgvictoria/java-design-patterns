package org.victoria.factory;

import org.victoria.factory.factory.Factory;
import org.victoria.factory.model.Circle;
import org.victoria.factory.model.Rectangle;
import org.victoria.factory.registry.SwitchRegistry;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PlayWithSwitchRegistry {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        SwitchRegistry registry = new SwitchRegistry();

        Factory<Rectangle> rectangleFactory = (Factory<Rectangle>) registry.buildShapeFactory("rectangle");
        System.out.println("Rectangle = " + rectangleFactory.newInstance());
    }
}
