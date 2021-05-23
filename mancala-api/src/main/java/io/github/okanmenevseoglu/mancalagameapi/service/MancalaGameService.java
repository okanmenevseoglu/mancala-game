package io.github.okanmenevseoglu.mancalagameapi.service;

import io.github.okanmenevseoglu.mancalagameapi.dto.converter.BoardStatusResponseConverter;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.model.Board;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.MancalaGameRule;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.gameplay.SowRule;
import io.github.okanmenevseoglu.mancalagameapi.service.rules.validation.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class is the service layer of the game that handles the business logic.
 */
@Service
@Getter
public class MancalaGameService {

    private final BoardExistsValidationRule boardExistsRule;
    private final GameOverValidationRule gameOverRule;
    private final PlayerOneHomePitValidationRule playerOneHomePitRule;
    private final PlayerTwoHomePitValidationRule playerTwoHomePitRule;
    private final CurrentPlayerNonEmptyPitValidationRule currentPlayerNonEmptyPitRule;
    private final SowRule sowRule;

    private final BoardStatusResponseConverter boardStatusResponseConverter;

    private final Set<MancalaGameRule> gameRuleProcessors = new LinkedHashSet<>();

    private Board board;

    public MancalaGameService(BoardExistsValidationRule boardExistsRule, GameOverValidationRule gameOverRule, PlayerOneHomePitValidationRule playerOneHomePitRule,
                              PlayerTwoHomePitValidationRule playerTwoHomePitRule, CurrentPlayerNonEmptyPitValidationRule currentPlayerNonEmptyPitRule,
                              SowRule sowRule, BoardStatusResponseConverter boardStatusResponseConverter) {
        this.boardExistsRule = boardExistsRule;
        this.gameOverRule = gameOverRule;
        this.playerOneHomePitRule = playerOneHomePitRule;
        this.playerTwoHomePitRule = playerTwoHomePitRule;
        this.currentPlayerNonEmptyPitRule = currentPlayerNonEmptyPitRule;
        this.sowRule = sowRule;
        this.boardStatusResponseConverter = boardStatusResponseConverter;
    }

    /**
     * This is post construct method that runs after the constructor and collects all game rules processors.
     */
    @PostConstruct
    public void init() {
        gameRuleProcessors.addAll(Arrays.asList(
                boardExistsRule,
                gameOverRule,
                playerOneHomePitRule,
                playerTwoHomePitRule,
                currentPlayerNonEmptyPitRule,
                sowRule
        ));
    }

    /**
     * Initializes a new board with default stones.
     *
     * @return a board status response
     */
    public BoardStatusResponse initializeNewBoard() {
        this.board = new Board();
        return boardStatusResponseConverter.convert(board, "A new board has been initialized!");
    }

    /**
     * Gets the current board status.
     *
     * @return a board status response
     */
    public BoardStatusResponse getBoardStatus() {
        return boardStatusResponseConverter.convert(board);
    }

    /**
     * Sows a pit. The main rules of the game are defined in here. It using Chain of Responsibility pattern to traverse
     * through the rules and apply if necessary. To read more about the rules themselves, please refer to the README.md file.
     *
     * @return a board status response
     */
    public BoardStatusResponse sow(Integer pitIndex) {
        String message = "";

        // Validate sow rules
        for (MancalaGameRule rule : gameRuleProcessors) {
            message = rule.process(board, pitIndex);

            if (!message.isBlank())
                break;
        }

        return boardStatusResponseConverter.convert(board, message);
    }
}
