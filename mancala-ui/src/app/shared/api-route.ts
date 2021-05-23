export class ApiRoute {

  private static baseEndPoint = 'http://localhost:8082/game/';

  static getNewGameURL(): string {
    return this.baseEndPoint + 'new';
  }

  static getBoardURL(): string {
    return this.baseEndPoint + 'board';
  }

  static getSowURL(): string {
    return this.baseEndPoint + 'sow';
  }
}
