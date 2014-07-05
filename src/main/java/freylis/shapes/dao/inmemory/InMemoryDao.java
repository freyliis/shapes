/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package freylis.shapes.dao.inmemory;

import freylis.shapes.dao.GenericDao;
import freylis.shapes.model.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author freylis
 */
public class InMemoryDao implements GenericDao{
    
   private final List<Shape> inMemoryShapes = new ArrayList<>();

    @Override
    public void save(Shape shape) {
        inMemoryShapes.add(shape);
    }

    @Override
    public List<Shape> getAll() {
        return Collections.unmodifiableList(inMemoryShapes);
    }
    
    
    
}
