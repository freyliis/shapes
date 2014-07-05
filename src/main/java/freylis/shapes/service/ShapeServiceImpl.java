/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.dao.GenericDao;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import java.util.List;

/**
 *
 * @author freylis
 */
public class ShapeServiceImpl
        implements ShapeService {

    private final GenericDao dao;
    private final ShapeFactory shapeFactory;

    public ShapeServiceImpl(GenericDao dao, ShapeFactory factory) {
        this.dao = dao;
        this.shapeFactory = factory;
    }

    @Override
    public void addShape(String line) {
        Shape shape = shapeFactory.buildShape(line);
        if (shape != null) {
            dao.save(shape);
        }
    }

    @Override
    public double checkIfPointInsideShapes(ImmutablePoint point) {
        List<Shape> allShapes = dao.getAll();
        double totalSurface = 0.0;
        for (int i = 0; i < allShapes.size(); i++) {
            Shape shape = allShapes.get(i);
            if (shape.isInside(point, true)) {
                System.out.printf("Point is inside shape %u : %s", i, shape);
                System.out.printf("Shape surface: %f", shape.getSurface());
                totalSurface += shape.getSurface();
            }
        }
        return totalSurface;
    }

}
