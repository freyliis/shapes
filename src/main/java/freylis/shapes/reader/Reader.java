/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.reader;

import org.apache.log4j.Logger;

/**
 *
 * @author freylis
 */
public interface Reader {
    
    public static final Logger LOGGER = Logger.getLogger("CONSOLE");
    
    public void read();
    
}
