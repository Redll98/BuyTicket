package org.example.buyticket.app.rest.dto.base;

import org.example.buyticket.app.model.entity.base.AbstractEntity;

/**
 * Base class for all dto classes
 *
 * @author Gulyamov Rustam
 */
public abstract class BaseDTO<T extends AbstractEntity>  {
    /**
     * unique entity identifier
     */
    private int id;

    public void transform(T t) {
        id = t.getId();
    }

    public T untransform(T t) {
        t.setId(id);
        return t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
