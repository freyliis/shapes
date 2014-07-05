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
        Circle objectUnderTest = new Circle(0.0, new ImmutablePoint(1, 1));
        Double expResult = 0.0;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnProperSurface() {
        Circle objectUnderTest = new Circle(1.0, new ImmutablePoint(1, 1));
        Double expResult = Math.PI * Math.pow(1, 2);;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsInside() {
        ImmutablePoint point = new ImmutablePoint(1, 1);
        Circle instance = new Circle(1.0, new ImmutablePoint(1, 1));
        boolean expResult = true;
         boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnFalseIfPointIsNotInside() {
        ImmutablePoint point = new ImmutablePoint(5, 5);
        Circle instance = new Circle(1.0, new ImmutablePoint(1, 1));
        boolean expResult = false;
         boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsOnEdge() {
        ImmutablePoint point = new ImmutablePoint(1, 0);
        Circle instance = new Circle(1.0, new ImmutablePoint(1, 1));
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void shouldReturnTrueIfPointIsOnEdgeWithoutEdge() {
        ImmutablePoint point = new ImmutablePoint(1, 0);
        Circle instance = new Circle(1.0, new ImmutablePoint(1, 1));
        boolean expResult = false;
        boolean withEdge = false;
        boolean result = instance.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }
}
