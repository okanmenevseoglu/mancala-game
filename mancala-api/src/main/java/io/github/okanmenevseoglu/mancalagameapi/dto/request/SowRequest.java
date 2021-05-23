package io.github.okanmenevseoglu.mancalagameapi.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This class is the representation of a sow request.
 */
@Getter
@Setter
@NoArgsConstructor
public class SowRequest {

    /**
     * An integer that represents the pit index to be sowed. It needs to be between 0 and 13.
     */
    @Min(value = 0, message = "Pit index cannot be less than 0!")
    @Max(value = 13, message = "Pit index cannot be greater than 13!")
    @NotNull(message = "Pit index cannot be null!")
    private Integer pitIndex;

    /**
     * Constructor for all args.
     *
     * @param pitIndex an integer pit index
     */
    public SowRequest(Integer pitIndex) {
        this.pitIndex = pitIndex;
    }
}
