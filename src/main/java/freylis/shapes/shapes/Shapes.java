/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.shapes;

import freylis.shapes.dao.inmemory.InMemoryDao;
import freylis.shapes.factory.ShapeFactoryImpl;
import freylis.shapes.parsers.FileParser;
import freylis.shapes.parsers.HelpParser;
import freylis.shapes.reader.ConsoleReader;
import freylis.shapes.reader.Reader;
import freylis.shapes.service.PointServiceImpl;
import freylis.shapes.service.ShapeService;
import freylis.shapes.service.ShapeServiceImpl;

/**
 *
 * @author freylis
 */
public class Shapes {

    public static final String EXIT = "exit";
    public static final String HELP = "help";
    public static final String FILE = "file";

    public static final String DELIMITER = "\\s";
    private final Reader reader;
    private FileParser fileParser;

    public static void main(String[] args) {
        ShapeService shapeService = new ShapeServiceImpl(new InMemoryDao(), new ShapeFactoryImpl());
        Shapes shapes = new Shapes(new ConsoleReader(), new FileParser(new PointServiceImpl(shapeService), shapeService));
        shapes.runShapes();
    }

    public Shapes(Reader reader, FileParser fileParser) {
        this.reader = reader;
    }

    public void runShapes() {
        showPrompt();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            switch (line) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    HelpParser.printHelp();
                    break;
                default:
                    fileParser.parseLine(line);
            }
        }
    }

    private void showPrompt() {
        System.out.printf("Welcome in Shapes\n");
        System.out.printf("For help write help\n");
    }

}
