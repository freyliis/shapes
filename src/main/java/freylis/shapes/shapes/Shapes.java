/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.shapes;

import freylis.shapes.dao.inmemory.InMemoryDao;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import freylis.shapes.factory.ShapeFactoryImpl;
import freylis.shapes.reader.ConsoleReader;
import freylis.shapes.reader.FileReader;
import freylis.shapes.reader.Reader;
import freylis.shapes.service.ShapeService;
import freylis.shapes.service.ShapeServiceImpl;
import freylis.shapes.utils.MathUtils;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author freylis
 */
public class Shapes {

    public static final String EXIT = "exit";
    public static final String HELP = "help";
    public static final String FILE = "file";

    public static final String DELIMITER = "\\s";
    private final ShapeService shapeService;
    private final Reader reader;

    public static void main(String[] args) {
        final ShapeService shapeServiceImpl = new ShapeServiceImpl(new InMemoryDao(), new ShapeFactoryImpl());
        Shapes shapes = new Shapes(shapeServiceImpl, new ConsoleReader());
        shapes.runShapes();
    }

    public Shapes(ShapeService shapeService, Reader reader) {
        this.shapeService = shapeService;
        this.reader = reader;
    }

    public void runShapes() {
        showPrompt();
        reader.read();
    }



    private void checkPoint(String line) {
        List<Shape> shapes = shapeService.getShapes();
        List<Shape> shapesWherePointIsInside = shapeService.getShapesWherePointIsInside(point, shapes);
        double totalSurface = shapeService.getTotalSurface(shapesWherePointIsInside);
        System.out.printf("\nTotal shape surface: %f\n", totalSurface);
    }

    private void showPrompt() {
        System.out.printf("Welcome in Shapes\n");
        System.out.printf("For help write help\n");
    }

}
