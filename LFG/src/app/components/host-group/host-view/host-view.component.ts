import { Component, OnInit } from '@angular/core';
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

  group!: any;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.group = JSON.parse(this.tokenStorage.getCreatedGroup());
  }

  checkUsername(value: string) {
    if(value === this.group._groupLead._username) {
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
