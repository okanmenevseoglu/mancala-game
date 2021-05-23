import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MancalaGameComponent} from './mancala-game.component';

describe('MancalaGameComponent', () => {
  let component: MancalaGameComponent;
  let fixture: ComponentFixture<MancalaGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MancalaGameComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MancalaGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
