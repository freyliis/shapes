/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.util.List;

/**
 *
 * @author freylis
 */
public class PointServiceImpl implements PointService {

    private final ShapeService shapeService;

    public PointServiceImpl(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Override
    public void checkIfPointIsInsideShapes(ImmutablePoint point) {
        List<Shape> shapes = shapeService.getShapes();
        List<Shape> shapesWherePointIsInside = shapeService.getShapesWherePointIsInside(point, shapes);
        double totalSurface = shapeService.getTotalSurface(shapesWherePointIsInside);
        System.out.printf("\nTotal shape surface: %f\n", totalSurface);
    }

    @Override
    public ImmutablePoint parsePoint(String line) throws RuntimeException {
        String[] split = line.split(DELIMITER);
        if (split.length != 2) {
            throw new RuntimeException("Wrong number of parameters for point. Should be 2.");
        }
        return new ImmutablePoint(split[0], split[1]);
    }

}
