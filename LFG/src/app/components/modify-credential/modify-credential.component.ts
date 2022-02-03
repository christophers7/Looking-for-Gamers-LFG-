import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import BuildUser from 'src/app/utils/build-user';
import Validation from 'src/app/utils/validation';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-modify-credential',
  templateUrl: './modify-credential.component.html',
  styleUrls: ['./modify-credential.component.css']
})
export class ModifyCredentialComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    email: new FormControl('')
  });

  submitted = false;
  currentUser: any;

  constructor(private router: Router, private formBuilder: FormBuilder, private tokenStorage: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.setUpForm();
  }

  setUpForm(){
    this.currentUser = this.tokenStorage.getUser();
    this.form = this.formBuilder.group(
      {   
        username: [
          '',
          [
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        password: [
          '',
          [
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        confirmPassword: [
          '', 
          [
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ]},
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
    this.form.controls["username"].patchValue(this.currentUser.username);
  }

  goToProfile(): void {
    const navigationDetails: string[] = ['/main/profile'];
    this.router.navigate(navigationDetails);
  }

  passwordChanged: boolean = false;

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  
  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    let change:boolean = false;
    
    let tempUser = BuildUser.credentialBuilder(this.currentUser)

    let userN = this.form.get('username')?.value
    if(userN){
      tempUser._username = userN;
      if(this.currentUser._username != tempUser._username) change = true; 
    }

    let passW = this.form.get('password')?.value
    if(passW) {
      tempUser._password = passW;
      if(this.currentUser._password != tempUser._password) {
        change = true;
      }
    }
    console.log(tempUser)
    if(change) {
      this.userService.updateCredential(tempUser).subscribe(
        (data) => {
          this.tokenStorage.saveToken(data.jwt);
          let builtUser = BuildUser.userBuilder(data);
          this.tokenStorage.saveUser(builtUser);
          this.goToProfile();
      })
    }else{
      this.passwordChanged = false;
    }    
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }



}
