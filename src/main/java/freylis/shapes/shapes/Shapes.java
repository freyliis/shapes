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
import freylis.shapes.service.ShapeService;
import freylis.shapes.service.ShapeServiceImpl;
import freylis.shapes.utils.MathUtils;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    
    private final MathUtils mathUtils = new MathUtils();

    public static void main(String[] args) {
        Shapes shapes = new Shapes(new ShapeServiceImpl(new InMemoryDao(), new ShapeFactoryImpl()));
        shapes.runShapes();
    }

    public Shapes(ShapeService shapeService) {
        this.shapeService = shapeService;
    }
    
    
    public void runShapes() {
        Scanner scanner = new Scanner(System.in);
        scanner = scanner.useDelimiter(Pattern.compile(DELIMITER));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    printHelp();
                    break;
                default:
                    readLine(line);
            }
        }
    }


    private void readLine(String line) throws NumberFormatException {
        if (mathUtils.checkIfStartsWithNumber(line)) {
            checkPoint(line);
        } else if (line.startsWith(FILE)) {
            shapeService.readShapesFromFile(line);
        } else {
            addShape(line);
        }
    }

    private void printHelp() {
        System.out.println("Please tralalala");
    }

    private void addShape(String line) {
        Shape shape = shapeService.parseShape(line);
        shapeService.saveShape(shape);
    }

    private void checkPoint(String line) {
        ImmutablePoint point = createPoint(line);
        double totalSurface = shapeService.checkIfPointInsideShapes(point);
        System.out.printf("\nTotal shape surface: %f", totalSurface);
    }

    private ImmutablePoint createPoint(String line) {
        String[] split = line.split(DELIMITER);
        if (split.length != 2) {
            System.out.println("Too less parameters for point. Should be 2.");
            return null;
        }
        double xPosition = MathUtils.getDouble(split[0]);
        double yPosition = MathUtils.getDouble(split[1]);
        return new ImmutablePoint(xPosition, yPosition);
    }


    

}
