/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.reader;

import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author freylis
 */
public class ConsoleReader implements Reader {

    private final Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile(DELIMITER));
   
    
    @Override
    public String nextLine() {
       return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
    
   
}
