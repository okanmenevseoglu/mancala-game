package io.github.okanmenevseoglu.mancalagameapi.model;

import lombok.Getter;

/**
 * This class is the representation of a pit.
 */
@Getter
public class Pit {

    /**
     * A constant holding the default stones a pit can hold.
     */
    public static final Integer DEFAULT_STONES = 6;

    /**
     * Stones a pit hold.
     */
    private Integer stones;

    /**
     * Constructor to set defaults stones for a given pit index.
     */
    public Pit() {
        this(DEFAULT_STONES);
    }

    /**
     * Constructor to set defaults stones for a given pit index.
     *
     * @param stones an integer stones
     */
    public Pit(Integer stones) {
        setStones(stones);
    }

    /**
     * Setter for stones.
     *
     * @param stones an integer stones
     * @throws IllegalStateException if stones are less than 0
     */
    public void setStones(Integer stones) {
        validateIfStonesAreNotNegative(stones);

        this.stones = stones;
    }

    private void validateIfStonesAreNotNegative(Integer stones) {
        if (stones < 0)
            throw new IllegalStateException("Stones cannot be less than 0!");
    }
}
