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
public class TriangleTest {

    @Test
    public void shouldReturnProperSurface() {
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle instance = new Triangle(pointX, pointY, pointZ);
        Double expResult = 8.0; //0.5 * a * h = 0.5 * 4 * 4
        Double result = instance.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() {
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(0, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle instance = new Triangle(pointX, pointY, pointZ);
        Double expResult = null;
        Double result = instance.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnFalseIfPointIsNotInside() {
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle instance = new Triangle(pointX, pointY, pointZ);
        ImmutablePoint point = new ImmutablePoint(-1.0, 1);
        boolean expResult = false;
        boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsInside() {
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle instance = new Triangle(pointX, pointY, pointZ);
        ImmutablePoint point = new ImmutablePoint(1, 1);
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void shouldReturnTrueIfPointIsInsideOnEdge() {
        ImmutablePoint pointX = new ImmutablePoint(0, 0);
        ImmutablePoint pointY = new ImmutablePoint(4, 0);
        ImmutablePoint pointZ = new ImmutablePoint(2, 4);
        Triangle instance = new Triangle(pointX, pointY, pointZ);
        ImmutablePoint point = new ImmutablePoint(1, 0);
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

}
