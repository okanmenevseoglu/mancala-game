import {TestBed} from '@angular/core/testing';

import {MancalaGameService} from './mancala-game.service';

describe('MancalaGameService', () => {
  let service: MancalaGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MancalaGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
