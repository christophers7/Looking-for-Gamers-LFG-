import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { GameGroupService } from './game-group.service';

describe('GameGroupService', () => {
  let service: GameGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule,
        RouterTestingModule],
    });
    service = TestBed.inject(GameGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
