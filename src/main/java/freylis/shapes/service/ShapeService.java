/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import java.util.List;

/**
 *
 * @author freylis
 */
public interface ShapeService {

    public Shape parseShape(String line);

    public List<Shape> getShapes();

    public List<Shape> getShapesWherePointIsInside(ImmutablePoint point, List<Shape> allShapes);

    public double getTotalSurface(List<Shape> shapes);

    public void saveShape(Shape shape);

    public void readShapesFromFile(String line);
}
