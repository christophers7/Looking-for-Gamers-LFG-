import { TestBed } from '@angular/core/testing';

import { PollingRequestsService } from './polling-requests.service';

describe('PollingRequestsService', () => {
  let service: PollingRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PollingRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
