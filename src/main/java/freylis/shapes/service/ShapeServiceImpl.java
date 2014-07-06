/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.factory.ShapeFactory;
import freylis.shapes.dao.GenericDao;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author freylis
 */
public class ShapeServiceImpl implements ShapeService {

    private final GenericDao dao;
    private final ShapeFactory shapeFactory;
    private final ExecutorService newFixedThreadPool;

    public ShapeServiceImpl(GenericDao dao, ShapeFactory shapeFactory) {
        this.dao = dao;
        this.shapeFactory = shapeFactory;
        newFixedThreadPool = Executors.newFixedThreadPool(3);
    }

    @Override
    public void saveShape(Shape shape) {
        if (shape != null) {
            System.out.println("Saved: " + shape);
            dao.save(shape);
        }
    }

    @Override
    public List<Shape> getShapes() {
        List<Shape> allShapes = dao.getAll();
        return allShapes;
    }

    @Override
    public List<Shape> getShapesWherePointIsInside(ImmutablePoint point, List<Shape> allShapes) {
        List<Shape> shapesWithPointInside = new ArrayList<>();
        for (int i = 0; i < allShapes.size(); i++) {
            try {
                Future submit = newFixedThreadPool.submit(new ShapeTask(allShapes.get(i), i, point));
                Object shape = submit.get();
                if (shape != null) {
                    shapesWithPointInside.add((Shape) shape);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(ShapeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return shapesWithPointInside;
    }

    @Override
    public double getTotalSurface(List<Shape> shapes) {
        double totalSurface = 0.0;
        for (Shape shape : shapes) {
            totalSurface += shape.getSurface();
        }
        return totalSurface;
    }

    @Override
    public Shape parseShape(String line) {
        String[] split = line.split(DELIMITER);
        String shapeKind = split[0];
        return shapeFactory.buildShape(shapeKind, split);
    }
}
