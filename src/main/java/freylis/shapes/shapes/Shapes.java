/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.shapes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author freylis
 */
public class Shapes {

    public static final String EXIT = "exit";
    public static final String HELP = "help";

    public static void main(String[] args) {
        Shapes shapes = new Shapes();
        shapes.runShapes();
    }

    public void runShapes() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    printHelp();
                    break;
                default:
                    checkShape(line);
            }
        }
    }

    private void printHelp() {
        System.out.println("Please tralalala");
    }

    private void checkShape(String line) {
        System.out.println(line);
    }

}
