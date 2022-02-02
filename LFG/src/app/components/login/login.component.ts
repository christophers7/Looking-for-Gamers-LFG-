import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService} from 'src/app/_services/token-storage.service';
import BuildUser from 'src/app/utils/build-user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),   
    password: new FormControl(''),
  });
  submitted = false;
  errorMessage = '';
  isLoginFailed = false;
  posts : any;

  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {   
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(20)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(30)
          ]
        ],      
      },
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    let userN = this.form.get('username')?.value
    let passW = this.form.get('password')?.value
    if(userN != null && passW != null) {
      this.authService.login(userN, passW).subscribe({
        next: data => {         
          this.isLoginFailed = false;
          this.tokenStorage.saveToken(data.jwt);
          let builtUser = BuildUser.userBuilder(data);
          this.tokenStorage.saveUser(builtUser)
          // this.tokenStorage.saveUser(data)
          this.router.navigate(['main'])   
        },
        error: err => {
          this.errorMessage = err.error.message;
          console.log("Login failed")
          this.isLoginFailed = true;
        }
      });        
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}
