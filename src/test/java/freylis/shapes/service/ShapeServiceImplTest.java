/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.factory.ShapeFactoryImpl;
import freylis.shapes.model.Circle;
import freylis.shapes.model.Donut;
import freylis.shapes.model.ImmutablePoint;
import freylis.shapes.model.Shape;
import freylis.shapes.model.Triangle;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author freylis
 */
public class ShapeServiceImplTest {

    public ShapeServiceImplTest() {
    }

    @Test
    public void shouldReturnTrueIfPointIsInsideCricleAndTriangle() {
        ShapeService service = new ShapeServiceImpl(null, null);
        ImmutablePoint immutablePoint = new ImmutablePoint(0, 0);
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        ImmutablePoint donutCenter = new ImmutablePoint(-5, -5);
        List<Shape> shapes = new ArrayList<>();
        Circle circle = new Circle(immutablePoint, 5.0);
        Triangle triangle = new Triangle(pointX, pointY, pointZ);
        Donut donut = new Donut(donutCenter, 2.0, 1.0);
        shapes.add(donut);
        shapes.add(triangle);
        shapes.add(circle);
        List<Shape> shapesWherePointIsInside = service.getShapesWherePointIsInside(immutablePoint, shapes);
        assertThat(shapesWherePointIsInside.size(), CoreMatchers.is(2));
    }

    @Test
    public void shouldReturnTrueIfTotalSurfaceCricleAndTriangle() {
        ShapeService service = new ShapeServiceImpl(null, null);
        List<Shape> shapes = new ArrayList<>();
        ImmutablePoint immutablePoint = new ImmutablePoint(0, 0);
        Circle circle = new Circle(immutablePoint, 1.0);
        Double expResultCircle = Math.PI * Math.pow(1, 2);;
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle triangle = new Triangle(pointX, pointY, pointZ);
        Double expResultTriangle = 8.0; //0.5 * a * h = 0.5 * 4 * 4
        Double expectedResult = expResultCircle + expResultTriangle;
        shapes.add(circle);
        shapes.add(triangle);
        double totalSurface = service.getTotalSurface(shapes);
        assertThat(totalSurface, CoreMatchers.is(expectedResult));
    }

    @Test
    public void shouldReturnTrueIfTriangleIsParsedProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "triangle 0 0 4 0 2 4";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrueIfTriangleIsParsedNotProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "triangle 0 0 4 ";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

    @Test
    public void shouldReturnTrueIfCircleIsParsedProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "circle 0 0 5";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrueIfCircleIsParsedNotProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "circle 0 0 5 0 0 0 ";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

    @Test
    public void shouldReturnTrueIfDonutIsParsedProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "donut 0 0 5 1";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnTrueIfDonutIsParsedNotProperly() {
        ShapeService service = new ShapeServiceImpl(null, new ShapeFactoryImpl());
        String line = "donut 0 s 1 5";
        Shape parseShape = service.parseShape(line);
        Assert.assertNotNull(parseShape);
    }

}
