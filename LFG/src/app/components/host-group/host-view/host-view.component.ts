import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-host-view',
  templateUrl: './host-view.component.html',
  styleUrls: ['./host-view.component.css']
})
export class HostViewComponent implements OnInit {

  currentUser: any;

  group!: any;

  // waitingRoom!: any;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.group = JSON.parse(this.tokenStorage.getCreatedGroup());
  }

  checkUsername(value: string) {
    if(value === this.group._groupLead.username) {
      return false;
    }
    else {
      return true;
    }
  }

  removing():void{
    console.log("removing member");
  }

  addingUserFromWaitingRoom(applicant:any): void{
    this.userService.respondToApplicant(applicant, true).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
        this.ngOnInit();
      }
    );
  }

  removingUserFromWaitingRoom(applicant:any): void{
    this.userService.respondToApplicant(applicant, false).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
        this.ngOnInit();
      }
    );
  }

  goMainPage():void{
    const navigationDetails: string[] = ['/main'];
    this.router.navigate(navigationDetails);
  }

  goToWaitingRoom(): void{
    this.userService.refreshGroupMemberList(this.group).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
      }
    );
  }

  convertToGroupFromWaitingListResponse(data:any):void{
    console.log(data);
    this.group._groupDetails = data.sessionDetails;
    this.group._groupMembers = data.groupMembers;    
    this.group._waitingUsers = data.waitingMembers;
    this.tokenStorage.saveCreatedGroup(this.group);
    console.log(this.group);
  }

  disbandGroup():void{
    this.userService.endSession();
    this.tokenStorage.removeCreatedGroup();
    this.goMainPage();
  }


}
