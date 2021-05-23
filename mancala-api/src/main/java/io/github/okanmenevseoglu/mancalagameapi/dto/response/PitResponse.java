package io.github.okanmenevseoglu.mancalagameapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the response DTO that represents pits to be sent to the client.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
public class PitResponse {

    /**
     * A final integer that represents the stones in a pit.
     */
    private Integer stones;

    /**
     * Constructor for all args.
     *
     * @param stones an integer stones
     */
    public PitResponse(Integer stones) {
        this.stones = stones;
    }
}
