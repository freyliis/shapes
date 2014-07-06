/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.factory;

import freylis.shapes.model.Circle;
import freylis.shapes.model.Donut;
import freylis.shapes.model.Shape;
import freylis.shapes.model.Triangle;
import static freylis.shapes.shapes.Shapes.DELIMITER;

/**
 *
 * @author freylis
 */
public class ShapeFactoryImpl implements ShapeFactory {

    private final String TRIANGLE = "triangle";
    private final String CIRCLE = "circle";
    private final String DONUT = "donut";
    
   

    @Override
    public Shape buildShape(String line) {
        String[] split = line.split(DELIMITER);
        String shapeKind = split[0];
        switch (shapeKind) {
            case TRIANGLE:
                return Triangle.constructTriangle(split);
            case CIRCLE:
                return Circle.constructCircle(split);
            case DONUT:
                return Donut.constructDonut(split);
            default:
                System.out.println("No shape with name " + shapeKind);
                return null;
        }
    }

}
