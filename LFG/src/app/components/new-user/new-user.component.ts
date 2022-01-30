import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Validation from 'src/app/utils/validation';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;
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
        email: [
          '', 
          [
            Validators.required, 
            Validators.email,
            Validators.maxLength(50)
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
        confirmPassword: [
          '', 
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(30)
          ]
        ],
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  goToLogin(): void {
    const navigationDetails: string[] = [''];
    this.router.navigate(navigationDetails);
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    console.log(JSON.stringify(this.form.value, null, 2));

    let userN = this.form.get('username')?.value
    let email = this.form.get('email')?.value
    let passW = this.form.get('password')?.value
    console.log(userN, email, passW)
    if(userN != null && passW != null) {
      this.authService.register(userN, email, passW).subscribe(
        (data) => {
          console.log(data.username);
          console.log(data.email)
          console.log(data.password)
        }) 
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}


