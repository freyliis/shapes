/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.model;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author freylis
 */
public class CircleTest {

    public CircleTest() {
    }

    @Test
    public void shouldReturnSurfaceZeroForZeroRadius() {
        Circle objectUnderTest = new Circle(0, new Point(1, 1));
        Double expResult = 0.0;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnProperSurface() {
        Circle objectUnderTest = new Circle(1, new Point(1, 1));
        Double expResult = Math.PI * Math.pow(1, 2);;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsInside() {
        Point point = new Point(1, 1);
        Circle instance = new Circle(1, new Point(1, 1));
        boolean expResult = true;
        boolean result = instance.isInside(point);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnFalseIfPointIsNotInside() {
        Point point = new Point(5, 5);
        Circle instance = new Circle(1, new Point(1, 1));
        boolean expResult = false;
        boolean result = instance.isInside(point);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsOnEdge() {
        Point point = new Point(1, 0);
        Circle instance = new Circle(1, new Point(1, 1));
        boolean expResult = true;
        boolean result = instance.isInside(point);
        assertThat(result, CoreMatchers.is(expResult));
    }
}
