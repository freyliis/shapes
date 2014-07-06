/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.factory;

import freylis.shapes.model.Circle;
import freylis.shapes.model.Donut;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import freylis.shapes.model.Triangle;
import freylis.shapes.utils.MathUtils;

/**
 *
 * @author freylis
 */
public class ShapeFactoryImpl implements ShapeFactory {

    private final String TRIANGLE = "triangle";
    private final String CIRCLE = "circle";
    private final String DONUT = "donut";

    @Override
    public Shape buildShape(String shapeKind, String... line) {

        switch (shapeKind) {
            case TRIANGLE:
                return constructTriangle(line);
            case CIRCLE:
                return constructCircle(line);
            case DONUT:
                return constructDonut(line);
            default:
                System.out.println("No shape with name " + shapeKind);
                return null;
        }
    }

    private Shape constructTriangle(String[] parameters) {
        checkParametersAreOk(parameters.length, 7);
        ImmutablePoint pointX = new ImmutablePoint(MathUtils.getDouble(parameters[1]), MathUtils.getDouble(parameters[2]));
        ImmutablePoint pointY = new ImmutablePoint(MathUtils.getDouble(parameters[3]), MathUtils.getDouble(parameters[4]));
        ImmutablePoint pointZ = new ImmutablePoint(MathUtils.getDouble(parameters[5]), MathUtils.getDouble(parameters[6]));
        return new Triangle(pointX, pointY, pointZ);
    }

    public Circle constructCircle(String[] parameters) {
        checkParametersAreOk(parameters.length, 4);
        ImmutablePoint center = new ImmutablePoint(MathUtils.getDouble(parameters[1]), MathUtils.getDouble(parameters[2]));
        Double radius = MathUtils.getDouble(parameters[3]);
        return new Circle(center, radius);
    }

    public Donut constructDonut(String[] parameters) {
        checkParametersAreOk(parameters.length, 5);
        ImmutablePoint center = new ImmutablePoint(MathUtils.getDouble(parameters[1]), MathUtils.getDouble(parameters[2]));
        Double radiusOuter = MathUtils.getDouble(parameters[3]);
        Double radiusInner = MathUtils.getDouble(parameters[4]);
        return new Donut(center, radiusOuter, radiusInner);
    }

    private void checkParametersAreOk(int size, int properSize) {
        if (size != properSize) {
            throw new IllegalArgumentException("Wrong number of parameters for donut: " + size + ". Should be " + properSize);
        }
    }

}
