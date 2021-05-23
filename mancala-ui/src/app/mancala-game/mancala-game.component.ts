import {Component, OnInit} from '@angular/core';
import {MancalaGameService} from "./mancala-game.service";
import {BoardStatusResponse} from "../response/board-status-response";
import {SowRequest} from "../request/sow-request";
import {ErrorResponse} from "../response/error-response";

@Component({
  selector: 'mancala-game',
  templateUrl: './mancala-game.component.html',
  styleUrls: ['./mancala-game.component.css'],
  providers: [MancalaGameService]
})
export class MancalaGameComponent implements OnInit {

  boardStatusResponse?: BoardStatusResponse;

  errorResponse?: ErrorResponse;

  constructor(private mancalaGameService: MancalaGameService) {
  }

  ngOnInit(): void {
    this.doGetBoardStatus();
  }

  public doGetNewGame() {
    this.mancalaGameService.getNewGame().subscribe(data => {
      this.boardStatusResponse = data;
    }, (error) => {
      this.errorResponse = error;
    });
  }

  public doGetBoardStatus() {
    this.mancalaGameService.getBoardStatus().subscribe(data => {
      this.boardStatusResponse = data;
    }, (error) => {
      this.errorResponse = error;
    });
  }

  public doSow(pitIndex: number) {
    let sowRequest = new SowRequest();
    sowRequest.pitIndex = pitIndex;

    this.mancalaGameService.postSow(sowRequest).subscribe(data => {
      this.boardStatusResponse = data;
    }, (error) => {
      this.errorResponse = error;
    });
  }
}
