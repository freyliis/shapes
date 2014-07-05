/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.shapes;

import freylis.shapes.dao.inmemory.InMemoryDao;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.service.ShapeFactory;
import freylis.shapes.service.ShapeFactoryImpl;
import freylis.shapes.service.ShapeService;
import freylis.shapes.service.ShapeServiceImpl;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author freylis
 */
public class Shapes {

    public static final String EXIT = "exit";
    public static final String HELP = "help";
    private final String pointPattern = "^(-)?\\d+\\.?\\d*";
    public static final String DELIMITER = "\\s";
    private final ShapeService shapeService;
    
    

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
        Pattern pattern = Pattern.compile(pointPattern);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            checkPoint(line);
        } else {
            checkShape(line);
        }
    }

    private void printHelp() {
        System.out.println("Please tralalala");
    }

    private void checkShape(String line) {
        shapeService.addShape(line);
    }

    private void checkPoint(String line) {
        ImmutablePoint point = createPoint(line); 
        shapeService.checkIfPointInsideShapes(point);

    }

    private ImmutablePoint createPoint(String line) {
        String[] split = line.split(DELIMITER);
        if (split.length != 2) {
            System.out.println("Too less parameters for point. Should be 2.");
            return null;
        }
        double xPosition = getDouble(split[0]);
        double yPosition = getDouble(split[1]);
        return new ImmutablePoint(xPosition, yPosition);
    }

    public static Double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong number format");
            throw ex;
        }
    }

}
