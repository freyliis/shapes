/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.model.ImmutablePoint;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author freylis
 */
public class PointServiceImplTest {

    @Test
    public void shouldReturnProperPoint() {
        PointServiceImpl impl = new PointServiceImpl(null);
        String line = "0 0";
        ImmutablePoint parsePoint = impl.parsePoint(line);
        assertNotNull(parsePoint);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExcpetionForWrongNumberOfParameters() {
        PointServiceImpl impl = new PointServiceImpl(null);
        String line = "0 0 0";
        ImmutablePoint parsePoint = impl.parsePoint(line);
        assertNotNull(parsePoint);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExcpetionForWrongParameters() {
        PointServiceImpl impl = new PointServiceImpl(null);
        String line = "0 a";
        ImmutablePoint parsePoint = impl.parsePoint(line);
        assertNotNull(parsePoint);
    }

}
