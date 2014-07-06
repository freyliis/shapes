/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.utils;

import freylis.shapes.shapes.Shapes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author freylis
 */
public class MathUtils {

    private final String pointPattern = "^(-)?\\d+\\.?\\d*.*";

    public static Double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong number format");
            throw ex;
        }
    }

    public boolean checkIfStartsWithNumber(String line) {
        Pattern pattern = Pattern.compile(pointPattern);
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

}
