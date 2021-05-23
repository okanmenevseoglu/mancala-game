package io.github.okanmenevseoglu.mancalagameapi.dto.converter;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * A unit test class that contains the tests of the {@link BoardStatusResponseConverter} class.
 */
@ExtendWith(MockitoExtension.class)
class BoardStatusResponseConverterTest {

    @Mock
    private PitResponseConverter pitResponseConverter;

    @InjectMocks
    private BoardStatusResponseConverter boardStatusResponseConverter;

    @Test
    void shouldConvertFromBoardToBoardResponse() {
        Board board = new Board();

        PitResponse pitResponse1 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse2 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse3 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse4 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse5 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse6 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse7 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse8 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse9 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse10 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse11 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse12 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse13 = PitResponse.builder().stones(6).build();
        PitResponse pitResponse14 = PitResponse.builder().stones(6).build();

        PitResponse[] pitResponses = {pitResponse1, pitResponse2, pitResponse3, pitResponse4, pitResponse5, pitResponse6,
                pitResponse7, pitResponse8, pitResponse9, pitResponse10, pitResponse11, pitResponse12, pitResponse13, pitResponse14};

        when(pitResponseConverter.convertFromArrayToArray(board.getPits())).thenReturn(pitResponses);

        BoardStatusResponse boardStatusResponse = boardStatusResponseConverter.convert(board, "Test Message");

        assertThat(boardStatusResponse.getCurrentPlayer()).isEqualTo("ONE");
        assertThat(boardStatusResponse.getPits()).isEqualTo(pitResponses);
        assertThat(boardStatusResponse.getInfoMessage()).isEqualTo("Test Message");

        board.setCurrentPlayer(null);

        boardStatusResponse = boardStatusResponseConverter.convert(board);

        assertThat(boardStatusResponse.getPits()).isEqualTo(pitResponses);
        assertThat(boardStatusResponse.getCurrentPlayer()).isNull();
        assertThat(boardStatusResponse.getInfoMessage()).isNull();
    }

    @Test
    void shouldReturnBoardNotFoundMessageIfBoardIsNull() {
        BoardStatusResponse boardStatusResponse = boardStatusResponseConverter.convert(null);

        assertThat(boardStatusResponse.getPits()).isNull();
        assertThat(boardStatusResponse.getCurrentPlayer()).isNull();
        assertThat(boardStatusResponse.getInfoMessage()).isEqualTo("The board is not found. Please initialize a new board!");
    }
}
