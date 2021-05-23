package io.github.okanmenevseoglu.mancalagameapi.service.rules.validation;

import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A unit test class that contains the tests of the {@link BoardExistsValidationRule} class.
 */
@ExtendWith(MockitoExtension.class)
class BoardExistsValidationRuleTest {

    @InjectMocks
    private BoardExistsValidationRule rule;

    @Test
    void shouldValidateBoardExistsRule() {
        assertThat(rule.isValidated(null, 5)).isFalse();
        assertThat(rule.isValidated(new Board(), 5)).isTrue();
    }

    @Test
    void shouldGetMessage() {
        Board board = new Board();

        assertThat(rule.getMessage(board)).isEqualTo("The board is not found. Please initialize a new board!");
    }
}
