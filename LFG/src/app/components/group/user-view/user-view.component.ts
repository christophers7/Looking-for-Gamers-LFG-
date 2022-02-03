import { Component, OnInit } from '@angular/core';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  joinedSession!: any;

  insideSession: boolean = false

  insideGroup!: any;

  constructor(
    private userService: UserService,
    private sessionStorage: SessionStorageService,
    private routingAllocation: RoutingAllocatorService
  ) { }

  ngOnInit(): void {
    this.insideSession = false;
    this.isInsideSession();
  }

  isInsideSession(): void {
    let thing = this.sessionStorage.getWaitingGroups();
    if(!thing){
    this.joinedSession = JSON.parse(this.sessionStorage.getWaitingGroups());
    for (let i: number = 0; i < this.joinedSession.length; i++) {
      this.userService.refreshWaitingList(this.joinedSession[i].groupId).subscribe(
        (data) => {
          if (data.success) {
            this.insideSession = true;
            this.insideGroup = data;
          }
        }
      );
    }
    }
  }

  goMainPage(): void {
    this.routingAllocation.main();
  }

  leaveSession(): void {
    console.log(this.insideGroup);
    this.userService.leaveAllWaitingList().subscribe(
      (data) => {
        console.log(data)
        this.sessionStorage.leaveAllGroups();
      }
    );

    this.goMainPage();
  }


}
