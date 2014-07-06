/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.reader;

import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.parsers.HelpParser;
import static freylis.shapes.shapes.Shapes.DELIMITER;
import static freylis.shapes.shapes.Shapes.EXIT;
import static freylis.shapes.shapes.Shapes.FILE;
import static freylis.shapes.shapes.Shapes.HELP;
import freylis.shapes.utils.MathUtils;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author freylis
 */
public class ConsoleReader implements Reader {

    @Override
    public void read() {
        Scanner scanner = new Scanner(System.in);
        scanner = scanner.useDelimiter(Pattern.compile(DELIMITER));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    HelpParser.printHelp();
                    break;
                default:
                    readLine(line);
            }
        }
    }

    private void readLine(String line) {
        try {
            if (MathUtils.checkIfStartsWithNumber(line)) {
                parsePoint(line);
            } else if (line.startsWith(FILE)) {
                fileService.readShapesFromFile(line);
            } else {
                addShape(line);
            }
        } catch (RuntimeException ex) {
            LOGGER.info(ex);
        }
    }

    private ImmutablePoint parsePoint(String line) throws RuntimeException {
        String[] split = line.split(DELIMITER);
        if (split.length != 2) {
            throw new RuntimeException("Wrong number of parameters for point. Should be 2.");
        }
        return new ImmutablePoint(split[0], split[1]);
    }

}
