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

    private final double radius;
    private final Point center;

    public Circle(double radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
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
    public boolean isInside(Point point) {
        final double xDifference = point.getxPosition() - this.center.getxPosition();
        final double yDifference = point.getyPosition() - this.center.getyPosition();
        return (Math.pow(xDifference, 2) + Math.pow(yDifference, 2)) <= Math.pow(this.radius,2);
    }
}
