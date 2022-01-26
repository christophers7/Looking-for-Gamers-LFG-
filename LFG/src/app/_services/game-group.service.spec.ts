import { TestBed } from '@angular/core/testing';

import { GameGroupService } from './game-group.service';

describe('GameGroupService', () => {
  let service: GameGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
