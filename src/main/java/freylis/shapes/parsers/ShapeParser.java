/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.parsers;

import freylis.shapes.factory.ShapeFactory;
import freylis.shapes.model.Shape;
import static freylis.shapes.shapes.Shapes.DELIMITER;

/**
 *
 * @author freylis
 */
public class ShapeParser {

    private final ShapeFactory shapeFactory;

    public ShapeParser(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Shape parseShape(String line) {
        String[] split = line.split(DELIMITER);
        String shapeKind = split[0];
        return shapeFactory.buildShape(shapeKind, split);
    }

}
