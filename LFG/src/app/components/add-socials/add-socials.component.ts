import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-add-socials',
  templateUrl: './add-socials.component.html',
  styleUrls: ['./add-socials.component.css']
})
export class AddSocialsComponent implements OnInit {

  form: FormGroup = new FormGroup({
    steamId: new FormControl(''),   
  });
  submitted = false;
  errorMessage = '';
  isLinkFailed = false;
  posts : any;

  constructor(private router: Router, private formBuilder: FormBuilder, private userService: UserService, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {   
        steamId: [
          '',
          [
            Validators.required,
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
          console.log(data);
          this.isLinkFailed = false;
          let builtUser = BuildUser.userBuilder(data);
          console.log(builtUser);
          this.tokenStorage.saveUser(builtUser)
          // this.tokenStorage.saveUser(data)
          this.router.navigate(['main'])   
        },
        error: err => {
          this.errorMessage = err.error.message;
          console.log("Link unsuccessful.")
          this.isLinkFailed = true;
        }
      });        
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}

}
