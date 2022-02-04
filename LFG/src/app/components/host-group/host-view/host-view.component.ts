import { Component, OnInit } from '@angular/core';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { PollingRequestsService } from 'src/app/_services/sessions/polling-requests.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-host-view',
  templateUrl: './host-view.component.html',
  styleUrls: ['./host-view.component.css']
})
export class HostViewComponent implements OnInit {

  currentUser: any;
  stats: any;
  group!: any;

  constructor(
    private sessionStorage: SessionStorageService,
    private tokenStorage: TokenStorageService,
    private routingAllocator: RoutingAllocatorService,
    private userService: UserService,
    private pollingService: PollingRequestsService) { }

  ngOnInit(): void {
    this.initializeView();
  }

  initializeView():void{
    this.currentUser = this.tokenStorage.getUser();
    this.stats = this.tokenStorage.getSocials();
    this.group = JSON.parse(this.sessionStorage.getCreatedGroup());
    if(this.group.groupId != 0) {
      this.pollingService.updateMembers(this.group);
    }
  }

  checkUsername(value: string) {
    return value !== this.group._groupLead.username; 
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
    this.routingAllocator.main();
  }

  goToWaitingRoom(): void{
    this.userService.refreshGroupMemberList(this.group).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
      }
    );
  }

  convertToGroupFromWaitingListResponse(data:any):void{
    this.group._groupDetails = data.sessionDetails;
    this.group._groupMembers = data.groupMembers;    
    this.group._waitingUsers = data.waitingMembers;
    this.sessionStorage.saveCreatedGroup(this.group);
  }

  disbandGroup():void{
    this.userService.endSession();
    this.sessionStorage.removeCreatedGroup();
    this.pollingService.ngOnDestroy();
    this.goMainPage();
  }

  viewAchievements(groupUser: any) {
    window.open(groupUser.achievements);
  }


}
