import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { HostViewComponent } from './host-view.component';

describe('HostViewComponent', () => {
  let component: HostViewComponent;
  let fixture: ComponentFixture<HostViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HostViewComponent ],
      imports: [RouterTestingModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HostViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
   expect(component).toBeTruthy();
  });
 
});
