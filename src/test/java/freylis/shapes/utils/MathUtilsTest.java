/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.utils;

import freylis.shapes.utils.MathUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author freylis
 */
public class MathUtilsTest {

    @Test
    public void shouldReturnTrueIfPoint3_0() {
        MathUtils mathUtils = new MathUtils();
        String line = "-3.0";
        boolean checkIfPoint = mathUtils.checkIfStartsWithNumber(line);
        assertThat(checkIfPoint, CoreMatchers.is(true));

    }

    @Test
    public void shouldReturnTrueIfPoint0_0() {
        MathUtils mathUtils = new MathUtils();
        String line = "0.0";
        boolean checkIfPoint = mathUtils.checkIfStartsWithNumber(line);
        assertThat(checkIfPoint, CoreMatchers.is(true));

    }

    @Test
    public void shouldReturnTrueIfPoint0() {
        MathUtils mathUtils = new MathUtils();
        String line = "0";
        boolean checkIfPoint = mathUtils.checkIfStartsWithNumber(line);
        assertThat(checkIfPoint, CoreMatchers.is(true));

    }

    @Test
    public void shouldReturnFalsIfPointA() {
        MathUtils mathUtils = new MathUtils();
        String line = "a";
        boolean checkIfPoint = mathUtils.checkIfStartsWithNumber(line);
        assertThat(checkIfPoint, CoreMatchers.is(false));

    }

    @Test
    public void shouldReturnTrueIfPoint0_0_0_0() {
        MathUtils mathUtils = new MathUtils();
        String line = "0.0 0.0";
        boolean checkIfPoint = mathUtils.checkIfStartsWithNumber(line);
        assertThat(checkIfPoint, CoreMatchers.is(true));

    }

}
