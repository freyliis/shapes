/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.factory;

import freylis.shapes.model.Shape;

/**
 *
 * @author freylis
 */
public interface ShapeFactory {
    
    public Shape buildShape(String line);
}
