import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyCredentialComponent } from './modify-credential.component';

describe('ModifyCredentialComponent', () => {
  let component: ModifyCredentialComponent;
  let fixture: ComponentFixture<ModifyCredentialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifyCredentialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyCredentialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
