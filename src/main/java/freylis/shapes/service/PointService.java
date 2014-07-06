/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.service;

import freylis.shapes.model.ImmutablePoint;

/**
 *
 * @author freylis
 */
public interface PointService {
    
    public void checkIfPointIsInsideShapes(ImmutablePoint point);
    
    public ImmutablePoint parsePoint(String line);
}
