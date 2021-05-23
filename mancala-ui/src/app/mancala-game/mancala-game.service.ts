import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BoardStatusResponse} from "../response/board-status-response";
import {Observable} from "rxjs";
import {ApiRoute} from "../shared/api-route";
import {SowRequest} from "../request/sow-request";

@Injectable({
  providedIn: 'root'
})
export class MancalaGameService {

  constructor(private httpClient: HttpClient) {
  }

  getNewGame(): Observable<BoardStatusResponse> {
    return this.httpClient.get<BoardStatusResponse>(ApiRoute.getNewGameURL());
  }

  getBoardStatus(): Observable<BoardStatusResponse> {
    return this.httpClient.get<BoardStatusResponse>(ApiRoute.getBoardURL());
  }

  postSow(sowRequest: SowRequest): Observable<BoardStatusResponse> {
    return this.httpClient.post<BoardStatusResponse>(ApiRoute.getSowURL(), sowRequest);
  }
}
