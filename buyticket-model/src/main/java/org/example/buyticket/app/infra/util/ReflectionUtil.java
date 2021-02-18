package org.example.buyticket.app.infra.util;

import org.example.buyticket.app.infra.exception.ConfigurationException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Contains reflection related operations
 *
 * @author Gulyamov Rustam
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * Creates an instance of the specified class. This method throws unchecked
     * exception if creation fails
     *
     * @param clz
     * @param <T>
     * @return
     * @throws ConfigurationException
     */
    public static <T> T createInstance(Class<T> clz)
            throws ConfigurationException {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConfigurationException(e);
        }
    }

    /**
     * Returns list of fields with identical names regardless of their
     * modifiers
     *
     * @param clazz1
     * @param clazz2
     * @return
     * @throws ConfigurationException
     */
    public static List<String> findSimilarFields(Class<?> clazz1, Class<?> clazz2)
            throws ConfigurationException {
        try {
            Field[] fields = clazz1.getDeclaredFields();
            List<String> targetFields = Stream.of(clazz2.getDeclaredFields())
                    .map((field) -> field.getName())
                    .collect(Collectors.toList());
            return Stream.of(fields)
                    .map((field) -> field.getName())
                    .filter((name) -> targetFields.contains(name))
                    .collect(Collectors.toList());
        } catch (SecurityException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }

    /**
     * Copies specified fields values from source to destination objects
     *
     * @param src
     * @param dest
     * @param fields
     * @throws ConfigurationException
     */
    public static void copyFields(Object src, Object dest, List<String> fields)
            throws ConfigurationException {
        Checks.checkParameter(src != null, "source object is not initialized");
        Checks.checkParameter(dest != null, "destination object is not initialized");
        try {
            for(String field: fields) {
                Field fld = src.getClass().getDeclaredField(field);
                if(fld != null) {
                    fld.setAccessible(true);
                    Object value = fld.get(src);

                    Field fldDest = dest.getClass().getDeclaredField(field);

                    if(fldDest != null) {
                        fldDest.setAccessible(true);
                        fldDest.set(dest, value);
                    }
                }
            }
        } catch(NoSuchFieldException | IllegalAccessException e) {
            throw new ConfigurationException(e);
        }
    }
}
