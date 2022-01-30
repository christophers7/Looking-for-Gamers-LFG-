import { ComponentFixture, TestBed } from '@angular/core/testing';
import HttpClientTestingModule from "@angular/cli"
import { LoginComponent } from './login.component';
import { AuthService } from 'src/app/_services/auth.service';
import { Observable } from 'rxjs';

export class MockLoginService extends AuthService {
  //override login(username: string, password: string): Observable<any> {
    
  //}
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientTestingModule],
      providers: [{provide: AuthService, MockLoginService}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('some login test', () => {
    expect(component.submitted)
  })
});
