/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author freylis
 */
public final class MathUtils {

    private final static String POINT_PATTERN = "^(-)?\\d+\\.?\\d*.*";

    private MathUtils() {
    }
    
    

    public static Double getDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong number format");
            throw ex;
        }
    }

    public static boolean checkIfStartsWithNumber(String line) {
        Pattern pattern = Pattern.compile(POINT_PATTERN);
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

}
