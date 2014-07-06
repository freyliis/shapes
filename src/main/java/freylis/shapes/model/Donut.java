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

    public static Donut constructDonut(String[] parameters) {
        if (parameters.length != 5) {
            System.out.println("Wrong number of parameters for donut: " + parameters.length);
            return null;
        } else {
            ImmutablePoint center = new ImmutablePoint(MathUtils.getDouble(parameters[1]), MathUtils.getDouble(parameters[2]));
            Double radiusOuter = MathUtils.getDouble(parameters[3]);
            Double radiusInner = MathUtils.getDouble(parameters[4]);
            return new Donut(center, radiusOuter, radiusInner);
        }
    }

    protected Donut(ImmutablePoint center, Double radiusOuter, Double radiusInner) {
        this.innerCircle = Circle.constructCircle(center, radiusInner);
        this.outerCircle = Circle.constructCircle(center, radiusOuter);
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
