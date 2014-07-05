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
class Point {
    
    private final double xPosition;
    private final double yPosition;

    public Point(double xPosition, double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "(" + xPosition + "," + yPosition + ')';
    }
    
    
}
