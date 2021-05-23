package io.github.okanmenevseoglu.mancalagameapi.dto.converter;

import java.util.function.Function;

/**
 * This a base converter interface which extends {@link Function} to generalize converters and be used in a functional
 * programming style.
 *
 * @param <IN>  an object to convert
 * @param <OUT> an object to be converted to
 */
public interface BaseDTOConverter<IN, OUT> extends Function<IN, OUT> {

    /**
     * A default wrapper method for the function call for the convert functionality.
     *
     * @param entity an object to convert
     * @return the converted object
     */
    default OUT convert(IN entity) {
        return apply(entity);
    }
}
