import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-host-view',
  templateUrl: './host-view.component.html',
  styleUrls: ['./host-view.component.css']
})
export class HostViewComponent implements OnInit {

  currentUser: any;

  @Input()
  group!: Group;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    console.log(this.group);
  }

  checkUsername(value: string) {
    if(value === this.group.groupLead.username) {
      return false;
    }
    else {
      return true;
    }
  }

  removing():void{
    console.log("removing member");
  }

  addingUserFromWaitingRoom(): void{
    console.log("adding suer from waiting room");
  }

  removingUserFromWaitingRoom(): void{
    console.log("removing user form waiting room");
  }

  goMainPage():void{
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }



}
