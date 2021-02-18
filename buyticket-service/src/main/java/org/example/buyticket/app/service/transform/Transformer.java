package org.example.buyticket.app.service.transform;

import org.example.buyticket.app.model.entity.base.AbstractEntity;
import org.example.buyticket.app.rest.dto.base.BaseDTO;

/**
 * Interface for convert business entities into DTO
 *
 * @author Gulyamov Rustam
 */
public interface Transformer {
    /**
     * Convert business entity into DTO
     *
     * @param entity
     * @param clazz
     * @param <T>
     * @param <P>
     * @return
     */
    <T extends AbstractEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clazz);

    /**
     * Convert DTO into business entity
     *
     * @param dto
     * @param clazz
     * @param <T>
     * @param <P>
     * @return
     */
    <T extends AbstractEntity, P extends BaseDTO<T>> T unTransform(P dto, Class<T> clazz);
}
