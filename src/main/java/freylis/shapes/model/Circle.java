/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

/**
 *
 * @author freylis
 */
public class Circle implements Shape {

    private final Double radius;
    private final ImmutablePoint center;

    public Circle(Double radius, ImmutablePoint center) {
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
