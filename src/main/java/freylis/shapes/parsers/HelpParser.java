/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.parsers;

/**
 *
 * @author freylis
 */
public class HelpParser {
    
     public static void printHelp() {
        System.out.println("\nShapes creation:"
                + "\n- circle, type \"circle x y r\" : the numbers are the x and y coordinates of the centre followed by the radius."
                + "\n- triangle, type \"triangle x1 y1 x2 y2 x3 y3\" : it is the x and y coordinates of the three vertices (six numbers in total)."
                + "\n- donut type \"donut x y radiusOuter radiusInner \" : it is the x and y of the centre followed by the two radiuses."
                + "\n\nPoint: "
                + "type \"x y \" program prints out all the shapes that include that point in the (x, y) space. "
                + "Also prints out the surface area of each shape found, and the total area of all the shapes returned for a given point"
                + "\n\nFile : for read from file type file \"pathTofile\""
                + "\n\nExit : for exit type exit");
    }
    
}
