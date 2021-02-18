package org.example.buyticket.app.service.transform.impl;

import org.example.buyticket.app.infra.util.Checks;
import org.example.buyticket.app.infra.util.CommonUtil;
import org.example.buyticket.app.infra.util.ReflectionUtil;
import org.example.buyticket.app.model.entity.base.AbstractEntity;
import org.example.buyticket.app.rest.dto.base.BaseDTO;
import org.example.buyticket.app.service.transform.Transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Default transformation engine that uses reflection to transform objects
 *
 * @author Gulyamov Rustam
 */
public class SimpleDTOTransformer implements Transformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDTOTransformer.class.getName());

    public SimpleDTOTransformer() {
    }

    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> P transform(final T entity, final Class<P> clazz) {
        checks(entity, clazz);
        P dto = ReflectionUtil.createInstance(clazz);
        ReflectionUtil.copyFields(entity, dto,
                ReflectionUtil.findSimilarFields(entity.getClass(), clazz));
        dto.transform(entity);

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransform: {} DTO", CommonUtil.toString(dto));
        }

        return dto;
    }

    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> T unTransform(final P dto, final Class<T> clazz) {
        checks(dto, clazz);
        T entity = ReflectionUtil.createInstance(clazz);
        ReflectionUtil.copyFields(dto, entity,
                ReflectionUtil.findSimilarFields(dto.getClass(), clazz));
        dto.untransform(entity);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransformer.transform: {} entity", CommonUtil.toString(entity));
        }

        return entity;
    }

    private void checks(final Object param, final Class<?> clazz) {
        Checks.checkParameter(param != null, "Source transformation object is not initialized");
        Checks.checkParameter(clazz != null, "No class is defined for transformation");
    }
}
