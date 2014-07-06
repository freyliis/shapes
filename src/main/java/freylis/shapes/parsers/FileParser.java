/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.parsers;

import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import freylis.shapes.reader.FileReader;
import static freylis.shapes.reader.Reader.LOGGER;
import freylis.shapes.service.PointService;
import freylis.shapes.service.ShapeService;
import static freylis.shapes.shapes.Shapes.FILE;
import freylis.shapes.utils.MathUtils;

/**
 *
 * @author freylis
 */
public class FileParser {

    private final PointService pointService;
    private final ShapeService shapeService;

    public FileParser(PointService pointService, ShapeService shapeService) {
        this.pointService = pointService;
        this.shapeService = shapeService;
    }

    public void parseLine(String line) {
        try {
            if (MathUtils.checkIfStartsWithNumber(line)) {
                ImmutablePoint parsedPoint = pointService.parsePoint(line);
                pointService.checkIfPointIsInsideShapes(parsedPoint);
            } else if (line.startsWith(FILE)) {
                FileReader fileReader = new FileReader();
                fileReader.init(line);
                while (fileReader.hasNextLine()) {
                    createShape(fileReader.nextLine());
                }
            } else {
                createShape(line);
            }
        } catch (RuntimeException ex) {
            LOGGER.info(ex);
        }
    }

    private void createShape(String line) {
        Shape parsedShape = shapeService.parseShape(line);
        shapeService.saveShape(parsedShape);
    }

}
