import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Validation from 'src/app/utils/validation';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import BuildUser from 'src/app/utils/build-user';

@Component({
  selector: 'app-modify-profile',
  templateUrl: './modify-profile.component.html',
  styleUrls: ['./modify-profile.component.css']
})
export class ModifyProfileComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;
  currentUser: any;

  constructor(private router: Router, private formBuilder: FormBuilder, private tokenStorage: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.form = this.formBuilder.group(
      {   
        username: [
          '',
          [
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        firstname: [
          '', 
          [
            Validators.maxLength(30)
          ]
        ],
        lastname: [
          '', 
          [
            Validators.maxLength(30)
          ]
        ],
        email: [
          '', 
          [ 
            Validators.email,
            Validators.maxLength(50)
          ]
        ],
        password: [
          '',
          [
            Validators.minLength(6),
            Validators.maxLength(30)
          ]
        ],
        confirmPassword: [
          '', 
          [
            Validators.minLength(6),
            Validators.maxLength(30)
          ]
        ],
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
    this.form.controls["username"].patchValue(this.currentUser.username);
    if(this.currentUser.firstName){
    this.form.controls["firstname"].patchValue(this.currentUser.firstName);
    }
    if(this.currentUser.lastName) {
      this.form.controls["lastname"].patchValue(this.currentUser.lastName);
    }
    this.form.controls["email"].patchValue(this.currentUser.email);
  }

  goToProfile(): void {
    const navigationDetails: string[] = ['/main/profile'];
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

    let tempUser = BuildUser.userBuilder(this.currentUser)

    let userN = this.form.get('username')?.value
    if(userN){
      tempUser._username = userN;
    }

    let firstN = this.form.get('firstname')?.value
    if(firstN) {
      tempUser._firstName = firstN;
    }

    let lastN = this.form.get('lastname')?.value
    if(lastN) {
      tempUser._lastName = lastN;
    }

    let eMail = this.form.get('email')?.value
    if(eMail) {
      tempUser._email = eMail;
    }

    let passW = this.form.get('password')?.value
    if(passW) {
      tempUser._password = passW;
    }
    console.log(tempUser)
    if(userN || firstN || lastN || eMail || passW) {
      this.userService.updateUser(tempUser).subscribe(
        (data) => {
          this.tokenStorage.saveToken(data.jwt);
          let builtUser = BuildUser.userBuilder(data);
          this.tokenStorage.saveUser(builtUser);
          this.goToProfile();
      })
    }    
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}
