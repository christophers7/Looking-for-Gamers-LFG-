import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGameGroupsComponent } from './view-game-groups.component';

describe('ViewGameGroupsComponent', () => {
  let component: ViewGameGroupsComponent;
  let fixture: ComponentFixture<ViewGameGroupsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewGameGroupsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGameGroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
