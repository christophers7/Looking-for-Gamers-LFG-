import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSocialsComponent } from './add-socials.component';

describe('AddSocialsComponent', () => {
  let component: AddSocialsComponent;
  let fixture: ComponentFixture<AddSocialsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSocialsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSocialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
