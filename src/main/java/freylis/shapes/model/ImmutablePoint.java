/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

import java.awt.Point;

/**
 *
 * @author freylis
 */
public class ImmutablePoint {

    private final Point.Double point;

    public ImmutablePoint(double xPosition, double yPosition) {
        this.point = new Point.Double(xPosition, yPosition);
    }

    public double getxPosition() {
        return point.getX();
    }

    public double getyPosition() {
        return point.getY();
    }

    public double distance(ImmutablePoint otherPoint) {
        return point.distance(otherPoint.getxPosition(), otherPoint.getyPosition());
    }

    @Override
    public String toString() {
        return "(" + getxPosition() + "," + getyPosition() + ')';
    }

}
