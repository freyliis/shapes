/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import java.util.concurrent.Callable;

/**
 *
 * @author freylis
 */
public class ShapeTask implements Callable {

    private final Shape shape;
    private final int index;
    private final ImmutablePoint point;

    public ShapeTask(Shape shape, int index, ImmutablePoint point) {
        this.shape = shape;
        this.index = index;
        this.point = point;
    }

    @Override
    public Object call() throws Exception {
        if (shape.isInside(point, true)) {
            System.out.printf("\nPoint %s is inside shape %d : %s . ", point, index, shape);
            System.out.printf("\nShape surface: %f", shape.getSurface());
            return shape;
        }
        return null;
    }

}
