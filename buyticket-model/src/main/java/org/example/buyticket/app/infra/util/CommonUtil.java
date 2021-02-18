package org.example.buyticket.app.infra.util;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

/**
 * Contains utility functions of the general purpose
 *
 * @author Gulyamov Rustam
 */
public class CommonUtil {

    private CommonUtil() {
    }

    /**
     * Returns not-null unmodifiable copy of the source set
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> Set<T> getSafeSet(Set<T> source) {
        return Collections.unmodifiableSet(Optional.ofNullable(source).orElse(Collections.emptySet()));
    }

    /**
     * Return not-null unmodifiable copy of the source list
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> List<T> getSafeList(List<T> source) {
        return Collections.unmodifiableList(Optional.ofNullable(source).orElse(Collections.emptyList()));
    }

    /**
     * Returns string representation of the object
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return ReflectionToStringBuilder.toString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
