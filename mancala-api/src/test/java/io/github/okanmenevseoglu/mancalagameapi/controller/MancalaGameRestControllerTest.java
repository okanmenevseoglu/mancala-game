package io.github.okanmenevseoglu.mancalagameapi.controller;

import io.github.okanmenevseoglu.mancalagameapi.dto.request.SowRequest;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.ErrorResponse;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.PitResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An integration test class that contains the tests of the {@link MancalaGameRestController} class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MancalaGameRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateNewGame() {
        ResponseEntity<BoardStatusResponse> response = this.restTemplate.getForEntity(getRequestURLFor("/new"), BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        BoardStatusResponse boardStatus = response.getBody();

        PitResponse[] pits = boardStatus.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 0 |-----------------------| 0 | Player 1 Home
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(6);
        assertThat(pits[1].getStones()).isEqualTo(6);
        assertThat(pits[2].getStones()).isEqualTo(6);
        assertThat(pits[3].getStones()).isEqualTo(6);
        assertThat(pits[4].getStones()).isEqualTo(6);
        assertThat(pits[5].getStones()).isEqualTo(6);

        assertThat(pits[6].getStones()).isEqualTo(0);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);

        assertThat(boardStatus.getCurrentPlayer()).isEqualTo("ONE");

        assertThat(boardStatus.getInfoMessage()).isEqualTo("A new board has been initialized!");
    }

    @Test
    void shouldGetBoard() {
        ResponseEntity<BoardStatusResponse> response = this.restTemplate.getForEntity(getRequestURLFor("/new"), BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        SowRequest sowRequest = new SowRequest(0);

        response = this.restTemplate.postForEntity(getRequestURLFor("/sow"), sowRequest, BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        response = this.restTemplate.getForEntity(getRequestURLFor("/board"), BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        BoardStatusResponse boardStatus = response.getBody();

        PitResponse[] pits = boardStatus.getPits();

        assertThat(boardStatus.getInfoMessage()).isEqualTo("It is Player ONE's turn!");
        assertThat(boardStatus.getCurrentPlayer()).isEqualTo("ONE");

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 0 |-----------------------| 1 | Player 1 Home
        //               |   | 0 | 7 | 7 | 7 | 7 | 7 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(7);
        assertThat(pits[2].getStones()).isEqualTo(7);
        assertThat(pits[3].getStones()).isEqualTo(7);
        assertThat(pits[4].getStones()).isEqualTo(7);
        assertThat(pits[5].getStones()).isEqualTo(7);

        assertThat(pits[6].getStones()).isEqualTo(1);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);
    }

    @Test
    void shouldPlayOneRoundGame() {
        ResponseEntity<BoardStatusResponse> response = this.restTemplate.getForEntity(getRequestURLFor("/new"), BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        BoardStatusResponse boardStatus = response.getBody();

        assertThat(boardStatus.getCurrentPlayer()).isEqualTo("ONE");
        assertThat(boardStatus.getInfoMessage()).isEqualTo("A new board has been initialized!");

        SowRequest sowRequest = new SowRequest(0);
        response = this.restTemplate.postForEntity(getRequestURLFor("/sow"), sowRequest, BoardStatusResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        boardStatus = response.getBody();

        PitResponse[] pits = boardStatus.getPits();

        // ------------------------Player 2 Pits------------------------
        //               |   | 6 | 6 | 6 | 6 | 6 | 6 |   |
        // Player 2 Home | 0 |-----------------------| 1 | Player 1 Home
        //               |   | 0 | 7 | 7 | 7 | 7 | 7 |   |
        // ------------------------Player 1 Pits------------------------

        assertThat(pits[0].getStones()).isEqualTo(0);
        assertThat(pits[1].getStones()).isEqualTo(7);
        assertThat(pits[2].getStones()).isEqualTo(7);
        assertThat(pits[3].getStones()).isEqualTo(7);
        assertThat(pits[4].getStones()).isEqualTo(7);
        assertThat(pits[5].getStones()).isEqualTo(7);

        assertThat(pits[6].getStones()).isEqualTo(1);

        assertThat(pits[7].getStones()).isEqualTo(6);
        assertThat(pits[8].getStones()).isEqualTo(6);
        assertThat(pits[9].getStones()).isEqualTo(6);
        assertThat(pits[10].getStones()).isEqualTo(6);
        assertThat(pits[11].getStones()).isEqualTo(6);
        assertThat(pits[12].getStones()).isEqualTo(6);

        assertThat(pits[13].getStones()).isEqualTo(0);

        assertThat(boardStatus.getCurrentPlayer()).isEqualTo("ONE");
        assertThat(boardStatus.getInfoMessage()).isEqualTo("Player ONE should select a pit to move.");
    }

    @Test
    void shouldReturnErrorResponseWhenTheRequestIsInvalid() {
        SowRequest sowRequest = new SowRequest(-1);

        ResponseEntity<ErrorResponse> response = this.restTemplate.postForEntity(getRequestURLFor("/sow"), sowRequest, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();

        ErrorResponse error = response.getBody();

        assertThat(error.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(error.getMessage()).isEqualTo("Pit index cannot be less than 0!");
        assertThat(error.getTimestamp()).isNotEmpty();

    }

    private String getRequestURLFor(String path) {
        return "http://localhost:" + port + "/game" + path;
    }
}
