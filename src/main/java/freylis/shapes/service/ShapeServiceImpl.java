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
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author freylis
 */
public class ShapeServiceImpl implements ShapeService {

    private final GenericDao dao;
    private final ShapeFactory shapeFactory;
    private final FileService fileService = new FileService();

    public ShapeServiceImpl(GenericDao dao, ShapeFactory factory) {
        this.dao = dao;
        this.shapeFactory = factory;
    }

    @Override
    public Shape parseShape(String line) {
        return shapeFactory.buildShape(line);
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
            Shape shape = allShapes.get(i);
            if (shape.isInside(point, true)) {
                System.out.printf("\nPoint is inside shape %d : %s . ", i, shape);
                System.out.printf("\nShape surface: %f", shape.getSurface());
                shapesWithPointInside.add(shape);

            }
        }
        return shapesWithPointInside;
    }

    public double getTotalSurface(List<Shape> shapes) {
        double totalSurface = 0.0;
        for (Shape shape : shapes) {
            totalSurface += shape.getSurface();
        }
        return totalSurface;
    }

    @Override
    public void readShapesFromFile(String line) {
        Charset charset = Charset.forName("US-ASCII");
        Path path = fileService.openFile(line);
        try (final BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String readLine = null;
            while ((readLine = reader.readLine()) != null) {
                Shape parseShape = parseShape(readLine);
                saveShape(parseShape);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

}
