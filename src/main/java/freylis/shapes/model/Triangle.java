/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

import freylis.shapes.shapes.Shapes;
import java.awt.geom.Line2D;

/**
 *
 * @author freylis
 */
public class Triangle implements Shape {

    private final ImmutablePoint pointX;
    private final ImmutablePoint pointY;
    private final ImmutablePoint pointZ;
    private final double halfOfcircuit;
    private final double distanceXY;
    private final double distanceYZ;
    private final double distanceZX;
    private Double surface;

    public static Triangle constructTriangle(String[] parameters) {
        if (parameters.length != 7) {
             System.out.println("Wrong number of parameters for triangle: " + parameters.length);
             return null;
        } else {
            ImmutablePoint pointX = new ImmutablePoint(Shapes.getDouble(parameters[1]), Shapes.getDouble(parameters[2]));
            ImmutablePoint pointY =new ImmutablePoint(Shapes.getDouble(parameters[3]), Shapes.getDouble(parameters[4]));
            ImmutablePoint pointZ = new ImmutablePoint(Shapes.getDouble(parameters[5]), Shapes.getDouble(parameters[6]));
            return new Triangle(pointX, pointY, pointZ);
        }
    }

    protected Triangle(ImmutablePoint pointX, ImmutablePoint pointY, ImmutablePoint pointZ) {
        this.distanceXY = pointX.distance(pointY);
        this.distanceYZ = pointY.distance(pointZ);
        this.distanceZX = pointZ.distance(pointX);

        if (!checkIfTriangle()) {
            throw new RuntimeException("Not a triangle");
        }
        this.pointX = pointX;
        this.pointY = pointY;
        this.pointZ = pointZ;

        this.halfOfcircuit = 0.5 * (this.distanceXY + this.distanceYZ + this.distanceZX);
    }

    public ImmutablePoint getPointX() {
        return pointX;
    }

    public ImmutablePoint getPointY() {
        return pointY;
    }

    public ImmutablePoint getPointZ() {
        return pointZ;
    }

    @Override
    public Double getSurface() {
        if (surface != null) {
            return surface;
        }
        double result = halfOfcircuit * (halfOfcircuit - distanceXY) * (halfOfcircuit - distanceYZ) * (halfOfcircuit - distanceZX);
        surface = Math.sqrt(result);
        return surface;

    }

    @Override
    public boolean isInside(ImmutablePoint point, boolean withEdge) {
        surface = getSurface();
        double s = 1 / (2 * surface) * (pointX.getyPosition() * pointZ.getxPosition() - pointX.getxPosition() * pointZ.getyPosition()
                + (pointZ.getyPosition() - pointX.getyPosition()) * point.getxPosition() + (pointX.getxPosition() - pointZ.getxPosition()) * point.getyPosition());
        double t = 1 / (2 * surface) * (pointX.getxPosition() * pointY.getyPosition() - pointX.getyPosition()
                * pointY.getxPosition() + (pointX.getyPosition() - pointY.getyPosition()) * point.getxPosition()
                + (pointY.getxPosition() - pointX.getxPosition()) * point.getyPosition());
        final boolean result = (s > 0) && (t > 0) && (1 - s - t > 0);
        if (!result && withEdge) {
            return isOnEdge(point);
        } else {
            return result;
        }
    }

    private boolean isOnEdge(ImmutablePoint point) {
        Line2D a = new Line2D.Double();
        Line2D b = new Line2D.Double();
        Line2D c = new Line2D.Double();

        a.setLine(pointX.getxPosition(), pointX.getyPosition(), pointY.getxPosition(), pointY.getyPosition());
        b.setLine(pointY.getxPosition(), pointY.getyPosition(), pointZ.getxPosition(), pointZ.getyPosition());
        c.setLine(pointZ.getxPosition(), pointZ.getyPosition(), pointX.getxPosition(), pointX.getyPosition());

        double pntX = point.getxPosition();
        double pntY = point.getyPosition();
        final double ptLineDist = a.ptLineDist(pntX, pntY);
        final double ptLineDist1 = b.ptLineDist(pntX, pntY);
        final double ptLineDist2 = c.ptLineDist(pntX, pntY);

        return (ptLineDist == 0 || ptLineDist1 == 0 || ptLineDist2 == 0);

    }

    private boolean checkIfTriangle() {

        return (distanceXY < distanceYZ + distanceZX) && (distanceYZ < distanceXY + distanceZX) && (distanceZX < distanceXY + distanceYZ);
    }

}
