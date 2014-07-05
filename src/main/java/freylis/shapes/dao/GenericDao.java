/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.dao;

import freylis.shapes.model.Shape;
import java.util.List;

/**
 *
 * @author freylis
 */
public interface GenericDao {
    
    public void save(Shape shape);
    
    public List<Shape> getAll();
}
