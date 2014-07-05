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
public class Donut implements Shape {

    private Circle innerCircle;
    private Circle outerCircle;

    public Donut(ImmutablePoint center, Double radiusOuter, Double radiusInner) {
        this.innerCircle = new Circle(radiusInner, center);
        this.outerCircle = new Circle(radiusOuter, center);
    }

    public ImmutablePoint getCenter() {
        return innerCircle.getCenter();
    }

    public Double getRadiusOuter() {
        return outerCircle.getRadius();
    }

    public Double getRadiusInner() {
        return innerCircle.getRadius();
    }

    @Override
    public String toString() {
        return "Donut with center at " + getCenter() + ", radius outer " + getRadiusOuter() + ", radius inner " + getRadiusInner();
    }

    @Override
    public Double getSurface() {
        return 4 * Math.pow(Math.PI, 2) * getRadiusOuter() * getRadiusInner();
    }

    @Override
    public boolean isInside(ImmutablePoint point, boolean withEdge) {
        return outerCircle.isInside(point, withEdge) && !innerCircle.isInside(point, !withEdge);
    }
}
