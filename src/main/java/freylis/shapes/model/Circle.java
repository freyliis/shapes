/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

import freylis.shapes.shapes.Shapes;

/**
 *
 * @author freylis
 */
public class Circle implements Shape {

    private final Double radius;
    private final ImmutablePoint center;

    public static Circle constructCircle(String[] parameters) {
        if (parameters.length != 4) {
            System.out.println("Wrong number of parameters for circle: " + parameters.length);
            return null;
        } else {
            ImmutablePoint center = new ImmutablePoint(Shapes.getDouble(parameters[1]), Shapes.getDouble(parameters[2]));
            Double radius = Shapes.getDouble(parameters[3]);
            return new Circle(center, radius);
        }
    }

    static Circle constructCircle(ImmutablePoint center, Double radius) {
        return new Circle(center, radius);
    }

    protected Circle(ImmutablePoint center, Double radius) {
        this.radius = radius;
        this.center = center;
    }

    public Double getRadius() {
        return radius;
    }

    public ImmutablePoint getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Circle with center at " + center + " and radius " + radius;
    }

    @Override
    public Double getSurface() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public boolean isInside(ImmutablePoint point, boolean withEdge) {
        final double xDifference = point.getxPosition() - center.getxPosition();
        final double yDifference = point.getyPosition() - center.getyPosition();
        double xSquare = Math.pow(xDifference, 2);
        double ySquare = Math.pow(yDifference, 2);
        double radiusSquare = Math.pow(radius, 2);
        if (withEdge) {
            return xSquare + ySquare <= radiusSquare;
        } else {
            return xSquare + ySquare < radiusSquare;
        }
    }
}
