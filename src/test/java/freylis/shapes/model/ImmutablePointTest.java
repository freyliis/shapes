/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.model;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author freylis
 */
public class ImmutablePointTest {
    
    
    @Test
    public void shouldReturnTrueIfDistance0() {
        ImmutablePoint otherPoint = new ImmutablePoint(0, 0);
        ImmutablePoint instance = new ImmutablePoint(0, 0);
        double expResult = 0.0;
        double result = instance.distance(otherPoint);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void shouldReturnTrueIfDistance1() {
        ImmutablePoint otherPoint = new ImmutablePoint(0, 0);
        ImmutablePoint instance = new ImmutablePoint(0, 1);
        double expResult = 1.0;
        double result = instance.distance(otherPoint);
        assertThat(result, CoreMatchers.is(expResult));
    }

    
}
