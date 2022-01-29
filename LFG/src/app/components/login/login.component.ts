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
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
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
          
          /**
           *  For testing till backend is done!!!!!
           */
          const user = data.find((a:any)=>{
            return a.username === userN && a.password === passW;
          });
          if(user){
            this.isLoginFailed = false;
            this.tokenStorage.saveToken(user.jwt);
            let builtUser = BuildUser.userBuilder(user);
            this.tokenStorage.saveUser(builtUser)
            this.router.navigate(['main'])
          }
          else {
            this.errorMessage = "Username or password incorrect";
            console.log("Username or password incorrect")
            this.isLoginFailed = true;
          }
          /**
           *  For testing till backend is done!!!!!
           */

//          this.isLoginFailed = false;
//          this.tokenStorage.saveToken(data.jwt);
//          this.tokenStorage.saveUser(data)
//          this.router.navigate(['main'])   
        },
        error: err => {
          this.errorMessage = err.error.message;
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
