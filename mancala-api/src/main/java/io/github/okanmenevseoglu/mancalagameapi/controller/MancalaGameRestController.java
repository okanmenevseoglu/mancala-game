package io.github.okanmenevseoglu.mancalagameapi.controller;

import io.github.okanmenevseoglu.mancalagameapi.dto.request.SowRequest;
import io.github.okanmenevseoglu.mancalagameapi.dto.response.BoardStatusResponse;
import io.github.okanmenevseoglu.mancalagameapi.service.MancalaGameService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is the rest controller of the game that handles client requests and responses.
 */
@RequestMapping("game")
@RestController
public class MancalaGameRestController {

    private final MancalaGameService mancalaGameService;

    public MancalaGameRestController(MancalaGameService mancalaGameService) {
        this.mancalaGameService = mancalaGameService;
    }

    /**
     * This controller is used for handling new game requests.
     *
     * @return a board status object
     */
    @GetMapping("new")
    public BoardStatusResponse newGame() {
        return mancalaGameService.initializeNewBoard();
    }

    /**
     * This controller is used for handling board status retrieval requests.
     *
     * @return a board status object
     */
    @GetMapping("board")
    public BoardStatusResponse getBoardStatus() {
        return mancalaGameService.getBoardStatus();
    }

    /**
     * This controller is used for handling sowing requests.
     *
     * @param sowRequest a sow request object
     * @return a board status object
     */
    @PostMapping("sow")
    public BoardStatusResponse sow(@Valid @RequestBody SowRequest sowRequest) {
        return mancalaGameService.sow(sowRequest.getPitIndex());
    }
}
