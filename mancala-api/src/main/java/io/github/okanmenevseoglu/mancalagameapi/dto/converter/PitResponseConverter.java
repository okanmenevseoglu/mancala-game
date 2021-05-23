package io.github.okanmenevseoglu.mancalagameapi.dto.converter;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Pit;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This is a converter that converts a {@link Pit} to a {@link PitResponse} DTO.
 */
@Component
public class PitResponseConverter implements BaseDTOConverter<Pit, PitResponse> {

    /**
     * This method overrides the functional apply method to be used in a singular conversion.
     *
     * @param pit a pit object to convert
     * @return a pit response object to be converted to
     * @throws NullPointerException if a pit is null
     */
    @Override
    public PitResponse apply(Pit pit) {
        return PitResponse.builder()
                .stones(pit.getStones())
                .build();
    }

    /**
     * This method uses streams to convert a pit array into a pit response array.
     *
     * @param pits a pit array to convert
     * @return a pit response array to be converted to
     * @throws NullPointerException if a pit is null
     */
    public PitResponse[] convertFromArrayToArray(Pit[] pits) {
        return Arrays.stream(pits)
                .map(pit -> new PitResponse(pit.getStones()))
                .toArray(PitResponse[]::new);
    }
}
