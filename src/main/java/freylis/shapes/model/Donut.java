/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

import freylis.shapes.shapes.Shapes;
import freylis.shapes.utils.MathUtils;

/**
 *
 * @author freylis
 */
public class Donut implements Shape {

    private final Circle innerCircle;
    private final Circle outerCircle;

    public Donut(ImmutablePoint center, Double radiusOuter, Double radiusInner) {
        if (!checkIfDonut(radiusOuter, radiusInner)) {
            throw new RuntimeException("Not a donut");
        }
        this.innerCircle = new Circle(center, radiusInner);
        this.outerCircle = new Circle(center, radiusOuter);
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

    private boolean checkIfDonut(Double radiusOuter, Double radiusInner) {
        return radiusOuter > radiusInner;
    }

}
