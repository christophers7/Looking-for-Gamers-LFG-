import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

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
  currentUser: any;
  panelNumber!: number;
  group!: Group;

  @Input()
  gameId!: number;

  @Output()
  panelNumberChange = new EventEmitter<number>();

  constructor(private router: Router, private formBuilder: FormBuilder, private userService: UserService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.form = this.formBuilder.group(
      {   
        maxGroupSize: [
          '',
          [
            Validators.required,
            Validators.maxLength(2)
          ]
        ],
        description: [
          '', 
          [
            Validators.required, 
            Validators.maxLength(100)
          ]
        ]
      }
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

    console.log(JSON.stringify(this.form.value, null, 2));

    let gSize = this.form.get('maxGroupSize')?.value
    let description = this.form.get('description')?.value
    console.log(gSize, description)
    if(gSize != null && description != null) {
      this.group = new Group(1, this.gameId, this.currentUser.username, gSize, 1, description)
      this.userService.createGroup(this.group).subscribe(
        (data) => {
          console.log(data);
        })
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  goToHostView() {
    this.panelNumber = 4;
    this.changePanel();
  }

  goBackToGroupSelect(): void{
    this.panelNumber = 2;
    this.changePanel();
  }

  changePanel() {
    this.panelNumberChange.emit(this.panelNumber);
  }
}


