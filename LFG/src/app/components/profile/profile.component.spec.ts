import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ProfileComponent } from './profile.component';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let router: RouterTestingModule;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileComponent ],
      imports: [RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    router = RouterTestingModule;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to Modify Profile when modifyProfile is called', () => {
    component.modifyProfile();
    expect(component.modifyProfile()).toHaveBeenCalled();
  });

  it('should navigate to main when goToMain is called', () => {
    component.goToMain();
    expect(component.goToMain()).toHaveBeenCalled();
  })
});
