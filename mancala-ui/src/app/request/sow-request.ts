export class SowRequest {

  pitIndex!: number;

  get _pitIndex(): number {
    return this.pitIndex;
  }

  set _pitIndex(value: number) {
    this.pitIndex = value;
  }
}
