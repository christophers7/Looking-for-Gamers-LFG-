import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PollingComponent } from './polling.component';

describe('PollingComponent', () => {
  let component: PollingComponent;
  let fixture: ComponentFixture<PollingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PollingComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PollingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
