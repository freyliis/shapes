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
public class DonutTest {

    @Test
    public void shouldReturn0IfOuterRadius0() {
        ImmutablePoint point = new ImmutablePoint(1, 1);
        Double outerRadius = 0.0;
        Double innerRadius = 4.0;
        Donut objectUnderTest = new Donut(point, outerRadius, innerRadius);
        Double expResult = 0.0;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnProperSurface() {
        ImmutablePoint point = new ImmutablePoint(1, 1);
        Double outerRadius = 5.0;
        Double innerRadius = 4.0;
        Donut objectUnderTest = new Donut(point, outerRadius, innerRadius);
        Double expResult = 4 * Math.pow(Math.PI, 2) * outerRadius * innerRadius;
        Double result = objectUnderTest.getSurface();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsInside() {
        ImmutablePoint point = new ImmutablePoint(1, 2);
        ImmutablePoint center = new ImmutablePoint(0, 0);
        Double outerRadius = 5.0;
        Double innerRadius = 1.0;
        Donut objectUnderTest = new Donut(center, outerRadius, innerRadius);
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = objectUnderTest.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnFalseIfPointIsInside() {
        ImmutablePoint point = new ImmutablePoint(0, 0);
        ImmutablePoint center = new ImmutablePoint(0, 0);
        Double outerRadius = 5.0;
        Double innerRadius = 1.0;
        Donut objectUnderTest = new Donut(center, outerRadius, innerRadius);
        boolean expResult = false;
        boolean withEdge = true;
        boolean result = objectUnderTest.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsOnInnerEdge() {
        ImmutablePoint point = new ImmutablePoint(1, 0);
        ImmutablePoint center = new ImmutablePoint(0, 0);
        Double outerRadius = 5.0;
        Double innerRadius = 1.0;
        Donut objectUnderTest = new Donut(center, outerRadius, innerRadius);
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = objectUnderTest.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void shouldReturnTrueIfPointIsOnOuterEdge() {
        ImmutablePoint point = new ImmutablePoint(5, 0);
        ImmutablePoint center = new ImmutablePoint(0, 0);
        Double outerRadius = 5.0;
        Double innerRadius = 1.0;
        Donut objectUnderTest = new Donut(center, outerRadius, innerRadius);
        boolean expResult = true;
        boolean withEdge = true;
        boolean result = objectUnderTest.isInside(point, withEdge);
        assertThat(result, CoreMatchers.is(expResult));
    }

}
