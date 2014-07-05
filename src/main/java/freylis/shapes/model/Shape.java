/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.model;

/**
 *
 * @author freylis
 */
public interface Shape {
    
    public Double getSurface();
    
    public boolean isInside(Point point);
    
}
