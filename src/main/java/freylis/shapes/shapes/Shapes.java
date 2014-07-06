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
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
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

    private final Logger logger = Logger.getLogger("CONSOLE");

    private final MathUtils mathUtils = new MathUtils();

    public static void main(String[] args) {
        Shapes shapes = new Shapes(new ShapeServiceImpl(new InMemoryDao(), new ShapeFactoryImpl()));
        shapes.runShapes();
    }

    public Shapes(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    public void runShapes() {
        showPrompt();
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

    private void readLine(String line) {
        try {
            if (mathUtils.checkIfStartsWithNumber(line)) {
                checkPoint(line);
            } else if (line.startsWith(FILE)) {
                shapeService.readShapesFromFile(line);
            } else {
                addShape(line);
            }
        } catch (RuntimeException ex) {
            logger.info(ex);
        }
    }

    private void printHelp() {
        System.out.println("\nShapes creation:"
                + "\n- circle, type \"circle x y r\" : the numbers are the x and y coordinates of the centre followed by the radius."
                + "\n- triangle, type \"triangle x1 y1 x2 y2 x3 y3\" : it is the x and y coordinates of the three vertices (six numbers in total)."
                + "\n- donut type \"donut x y radiusOuter radiusInner \" : it is the x and y of the centre followed by the two radiuses."
                + "\n\nPoint: "
                + "type \"x y \" program prints out all the shapes that include that point in the (x, y) space. "
                + "Also prints out the surface area of each shape found, and the total area of all the shapes returned for a given point"
                + "\n\nFile : for read from file type file \"pathTofile\""
                + "\n\nExit : for exit type exit");
    }

    private void addShape(String line) {
        Shape shape = shapeService.parseShape(line);
        shapeService.saveShape(shape);
    }

    private void checkPoint(String line) {
        ImmutablePoint point = createPoint(line);
        List<Shape> shapes = shapeService.getShapes();
        List<Shape> shapesWherePointIsInside = shapeService.getShapesWherePointIsInside(point, shapes);
        double totalSurface = shapeService.getTotalSurface(shapesWherePointIsInside);
        System.out.printf("\nTotal shape surface: %f\n", totalSurface);
    }

    private ImmutablePoint createPoint(String line) {
        String[] split = line.split(DELIMITER);
        if (split.length != 2) {
            throw new RuntimeException("Wrong number of parameters for point. Should be 2.");
        }
        double xPosition = MathUtils.getDouble(split[0]);
        double yPosition = MathUtils.getDouble(split[1]);
        return new ImmutablePoint(xPosition, yPosition);
    }

    private void showPrompt() {
        System.out.printf("Welcome in Shapes\n");
        System.out.printf("For help write help\n");
    }

}
