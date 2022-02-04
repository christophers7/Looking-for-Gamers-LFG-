import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NewUserComponent } from './new-user.component';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser'
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NewUserComponent', () => {
  let component: NewUserComponent;
  let fixture: ComponentFixture<NewUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewUserComponent ],
      imports: [RouterTestingModule,
      FormBuilder,
      FormsModule,
      ReactiveFormsModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to Login when goToLogin is called', () => {
    component.goToLogin()
    expect(component.goToLogin()).toHaveBeenCalled();
  })

  it('should submit new user data when form is submitted', () => {
    component.onSubmit()
    expect(component.onSubmit()).toHaveBeenCalled();
  })

  it('should reset form when onReset is called', () => {
    component.onReset()
    expect(component.onReset()).toHaveBeenCalled();
  })
});
