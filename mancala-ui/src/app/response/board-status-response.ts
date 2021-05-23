import {PitResponse} from "./pit-response";

export interface BoardStatusResponse {

  pits?: PitResponse[];

  currentPlayer: string;

  infoMessage: string;
}
